#include <SoftwareSerial.h>//시리얼 통신 라이브러리
#include "Servo.h"//서보 라이브러리

Servo myServo;//서보객체
#define BT_RXD 8//블루 받는 핀
#define BT_TXD 7//블루 보내는 핀
SoftwareSerial bluetooth(BT_RXD, BT_TXD);//시리얼 통신 위한 객체 선언
int angle=0;
char myChar='e';
 
void setup(){
  myServo.attach(12);//서보 시그널 핀 설정
  myServo.write(0);//서보 초기 각도 0도
  Serial.begin(9600);
  bluetooth.begin(9600);//블루투스 시리얼 개방ㅡ
}
 
void loop(){

  while(bluetooth.available()){//bluetooth값이 있으면
    myChar =(char)bluetooth.read();//bluetooth형식의 값을 char형식으로 변환
    delay(5);//수신 문자열 끊김 방지
  }

  if(myChar!='e'){//myString이 공백이 아니면
      Serial.println("input value : "+myChar);//시리얼 모니터에 myString값 출력
      switch(myChar){
        case 'u':
        if(angle<180){
           angle+=90;
        }
        myServo.write(angle);
        break;
        case 'd':
        if(angle>0){
          angle-=90;
        }
        myServo.write(angle);
        break;
        case 'i':
        angle=0;
        myServo.write(angle);
        Serial.println("0도로 초기화");//시리얼 모니터에 myString값 출력
        break;
        
      }
        myChar='e';//myString초기화 
  }
 
}
