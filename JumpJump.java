package jump;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JumpJump extends JFrame{
    private JLabel label;
    boolean flag=false;
    int x0,y0,x1,y1;
    
    public JumpJump(){
        super("微信跳一跳");//新建窗口
        this.setUndecorated(true);  
        this.setOpacity(0.7f);
        this.setSize(320,580);//宽高自设
        this.setVisible(true);//可见
//        this.dispose();
        this.setLocationRelativeTo(null);
        this.toFront();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("右键点击");
        this.add(label); 
      
        this.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e){  
                if(e.getButton() == MouseEvent.BUTTON3){ //3代表右键
                	if(!flag) {
                     x0 = e.getX();  
                     y0 = e.getY();  
                    	String banner = "鼠标当前点击位置的坐标是" + x0 + "," + y0;
                        label.setText(banner);  
                        flag=true;
                    }
                    else {
                    	x1=e.getX();
                    	y1=e.getY();
                    	double _x = Math.abs(x0 - x1);
                		double _y = Math.abs(y0 - y1);
                    	double dis=Math.sqrt(_x*_x+_y*_y);
                    	label.setText(Math.ceil(dis)*4.8+"");
                    	flag=false; 
                    	
                    	String cmd = "adb shell input touchscreen swipe 170 187 170 187 "+Math.round(dis*4.7);
                    		
                    	Runtime run = Runtime.getRuntime();
						try {
							Process pr = run.exec(cmd);
							System.out.println(cmd);
							pr.waitFor();
							
						} catch (Exception e1) {
							e1.printStackTrace();
							System.out.println(e1);
						}

                    }
                    
                }
            }
        });
    }
    public static void main(String[] args) {
        new JumpJump();
    }

}