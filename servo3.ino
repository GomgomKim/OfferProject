#include<Servo.h>
#include<SoftwareSerial.h>

SoftwareSerial BTSerial(2,3);
byte buffer[1024];
int bufferPosition;

Servo servo;
int motorAngle = 0;
int servoVal = 0;
int servoAngle = 90;

void setup() {
  // put your setup code here, to run once:

  servo.attach(9);

  BTSerial.begin(9600);
  Serial.begin(9600);
  bufferPosition =0;

}

void loop() {
  // put your main code here, to run repeatedly:
  if(BTSerial.available()){
      byte data = BTSerial.read();
      buffer[bufferPosition++]=data;

      Serial.println(data);

      switch(data){
          case 'j':
            servo.write(0); 
            break;
          case 'i':
            servo.write(45); 
            break;
          case 'k':
            servo.write(90); 
            break;
          case 'o':
            servo.write(135); 
            break;
          case 'l':
            servo.write(180); 
            break;
          case '\n':
            bufferPosition = 0;
            break;
          
        }
    }
}


void angleWrite(int angle){  // 버튼을 통해 서보모터를 제어하는 함수
  servo.write(angle); 
}


















