MyTest
====================

平时的一些测试小用例
---------------------
### ListView 中带pading的分割线
    在drawable下新建 listview_divider_inset.xml
    <?xml version="1.0" encoding="utf-8"?>
    <inset xmlns:android="http://schemas.android.com/apk/res/android" 
    android:insetLeft="15dp"
    android:insetRight="15dp"
    android:drawable="@color/listview_divider">
    </inset>
    
    在ListView中引用即可:
    <ListView 
    android:id="@+id/lv_test"
    android:layout_height="wrap_content"
    android:layout_width="match_parent"
    android:layout_below="@id/titlebar"
    android:scrollbars="none"
    android:divider="@drawable/listview_divider_inset"
    android:dividerHeight="0.35dp"
    />
    
    效果如下：
![github](https://github.com/handezhao/MyTest/raw/master/picture/divider.png)

### 将adapter中的点击事件移至Activity/Fragment中
        某些场合下我们试图把，adapter中的一些点击事件移至Activity/Fragment中
        看看怎么实现，我的思路是
        在Adapter中定义一个回调接口，在Activity中实现该接口，从而实现对点击事件的响应。
		
		public class TestAdapter extends BaseAdapter {
		
		private List<TestBean> list = new ArrayList<TestBean>();
		private Context context;
		private LayoutInflater inflater;
		
		public TestAdapter(ArrayList<TestBean> list, Context context) {
		this.context = context;
			this.list = list;
			this.inflater = LayoutInflater.from(context);
		}
	
		private ButtonListener buttonListener;
	
		public void setButtonListener(ButtonListener listener) {
			this.buttonListener = listener;
		}

		@Override
		public int getCount() {
			
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_test_bean, null);
				holder = new ViewHolder();
				holder.button = (Button) convertView.findViewById(R.id.button_item);
				holder.tvInfo = (TextView) convertView.findViewById(R.id.tv_info);
				holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
		
			holder.button.setText("try");
			holder.tvInfo.setText(list.get(position).getInfo());
			holder.tvName.setText(list.get(position).getName());
			holder.button.setOnClickListener(new OnClickListener() {
			
				@Override
				public void onClick(View view) {
					if (buttonListener != null) {
						buttonListener.onclick(position);
					}
				}
			});
		
		return convertView;
	}
	
		class ViewHolder {
			TextView tvName, tvInfo;
			Button button;
		}
		
		public interface ButtonListener {
			void onclick(int position);
		}
	}
	        
	        在fragment中实现就达到目的了
	        adapter.setButtonListener(new ButtonListener() {
					@Override
					public void onclick(int position) {
						Console.toast(getActivity(), String.valueOf(position));
					}
				});
			
### 一个自定义的CountsVie
		先看看效果，点击时数字加一，长按数字归零，同时加上按下效果：
![github](https://github.com/handezhao/MyTest/raw/master/picture/countView.gif)
		
		public class CountWidget extends View {
		private Paint paint;

		private Rect bounds;

		private int counts;
	
		private long lastTime;
	
		public CountWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setColor(Color.parseColor("#66CDAA"));
		bounds = new Rect();
	}
	

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			//画方框
			canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			//画数字
			Paint textPaint = new Paint();
			textPaint.setColor(Color.parseColor("#000000"));
			String text = String.valueOf(counts);
			textPaint.setTextSize(80);
			
			textPaint.getTextBounds(text, 0, text.length(), bounds);
			float textWidth = bounds.width();
			float textHeight = bounds.height();
			canvas.drawText(text, getWidth()/2 - textWidth/2, getHeight()/2 + textHeight/2, textPaint);
		}
	
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				paint.setColor(Color.parseColor("#3CB371"));
				counts++;
				lastTime = System.currentTimeMillis();
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
				paint.setColor(Color.parseColor("#66CDAA"));
				if ((System.currentTimeMillis() - lastTime) > 1200) {
					counts = 0;
				}
				invalidate();
				break;
			}
			return true;
			}
		}
		
### 关于属性动画和贝塞尔曲线
	先看看效果图
![github](https://github.com/handezhao/MyTest/raw/master/picture/bezier.gif)
	
	Android中的canvas可以用drawPath()配合path.quadTo()方法画贝塞尔曲线，而要做贝塞尔曲线的动画首先想到的就是自定义的TypeEvaluator
	只要我们能获取贝塞尔曲线上的任一点坐标就可以达到我们的目标了，下面看代码：
	public class BezierEvaluator implements TypeEvaluator<Point> {
	
	private Point mControlPoint;

        public BezierEvaluator(Point controlPoint) {
        this.mControlPoint = controlPoint;
    	}

	@Override
	public Point evaluate(float fraction, Point start, Point end) {
		//返回当前坐标值
		return BezierUtil.CalculateBezierPointForQuadratic(fraction, start, mControlPoint, end);
	}
	
	 /**
     	* B(t) = (1 - t)^2 * P0 + 2t * (1 - t) * P1 + t^2 * P2, t ∈ [0,1]
     	*二阶贝塞尔曲线上任一点坐标值算法
     	* @param t  曲线长度比例
     	* @param p0 起始点
     	* @param p1 控制点
     	* @param p2 终止点
     	* @return t对应的点
     	*/
   	 public static Point CalculateBezierPointForQuadratic(float t, Point p0, Point p1, Point p2) {
    		Point point = new Point();
       	 float temp = 1 - t;
       	 point.x = temp * temp * p0.x + 2 * t * temp * p1.x + t * t * p2.x;
       	 point.y = temp * temp * p0.y + 2 * t * temp * p1.y + t * t * p2.y;
       	 return point;
  	  }
	  
	  在我自定义的PathBezier中：
	  	public class PathBezier extends View {
		
		private static final String TAG = "PathBezier";
		
		private Paint mPathPaint;
		private Paint mCirclePaint;
		
		private int mStartPointX;
		private int mStartPointY;
		private int mEndPointX;
		private int mEndPointY;
		
		private int mMovePointX;
		private int mMovePointY;
		
		private int mControlPointX;
		private int mControlPointY;
	
		private Path mPath;
		private Paint linePaint;

		public PathBezier(Context context) {
			super(context);
		}

		public PathBezier(Context context, AttributeSet attrs) {
		        super(context, attrs);
		        mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	  	      mPathPaint.setStyle(Paint.Style.STROKE);
	 	       mPathPaint.setStrokeWidth(5);
	 	       mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			        
	 	       linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	 	       linePaint.setStyle(Paint.Style.STROKE);
	   		linePaint.setStrokeWidth(2);
	   		linePaint.setColor(Color.parseColor("#9c9c9c"));
	 	       linePaint.setAntiAlias(true);
	 		       
		        DisplayMetrics display = context.getResources().getDisplayMetrics();
		        float screenWidth = display.widthPixels;
		        float screenHeight = display.heightPixels;
	 	       mStartPointX = 100;
	 	       mStartPointY = 100;
	  			      
	   	     mEndPointX = (int) (screenWidth - 60);
	 	       mEndPointY = (int) (screenHeight - 400);
	        
	  	      Log.d(TAG, "screenWidth is " + screenWidth + " and screenHeight is " + screenHeight);
	 	       Log.d(TAG, "mEndPointX is " + mEndPointX + " and mEndPointY is " + mEndPointY);
		        mMovePointX = mStartPointX;
	 	       mMovePointY = mStartPointY;
		        mControlPointX = (int) (screenWidth/2);
	 	       mControlPointY = 100;
		        mPath = new Path();
		    }

		public PathBezier(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			mPath.reset();
			canvas.drawCircle(mStartPointX, mStartPointY, 30, mCirclePaint);
			canvas.drawCircle(mEndPointX, mEndPointY, 30, mCirclePaint);
			mPath.moveTo(mStartPointX, mStartPointY);
			mPath.quadTo(mControlPointX, mControlPointY, mEndPointX, mEndPointY);
			canvas.drawPath(mPath, mPathPaint);
			canvas.drawCircle(mMovePointX, mMovePointY, 30, mCirclePaint);
			canvas.drawCircle(mControlPointX, mControlPointY, 10, mCirclePaint);
			canvas.drawLine(mStartPointX, mStartPointY, mControlPointX, mControlPointY, linePaint);
			canvas.drawLine(mControlPointX, mControlPointY, mMovePointX, mMovePointY, linePaint);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mControlPointX = (int) event.getX();
				mControlPointY = (int) event.getY();
				invalidate();
				break;
			case MotionEvent.ACTION_MOVE:
				mControlPointX = (int) event.getX();
				mControlPointY = (int) event.getY();
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
				BezierEvaluator bezierEvaluator = new BezierEvaluator(new Point(mControlPointX, mControlPointY));
				Point start = new Point(mStartPointX, mStartPointY);
				Point end = new Point(mEndPointX, mEndPointY);
				ValueAnimator anim = ValueAnimator.ofObject(bezierEvaluator, start, end);
				anim.setDuration(3000);
				anim.setInterpolator(new AccelerateDecelerateInterpolator());
				anim.addUpdateListener(new AnimatorUpdateListener() {
				
					@Override
						public void onAnimationUpdate(ValueAnimator animation) {
						Point currentPoint = (Point) animation.getAnimatedValue();
						mMovePointX = (int) currentPoint.getX();
						mMovePointY = (int) currentPoint.getY();
						invalidate();
					}
				});
				anim.start();
				break;
			}
			return true;
		}

	}

	}
	

