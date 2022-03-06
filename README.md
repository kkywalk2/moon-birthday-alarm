### Redis Stream을 활용한 Telegram 생일봇

![](https://images.velog.io/images/kkywalk2/post/cc8f2938-ec7b-4ce6-9a1f-a61946b5b202/%EA%B7%B8%EB%A6%BC1.jpg)

- 텔레그램의 봇의 정보를 등록하고 이 봇에 알람을 원하는 날짜를 등록가능
- 특정주기로 API서버에서 알람에 해당하는 날짜가 되면 Redis Stream에 데이터를 전달
- 이를 Consumer에서 읽어 Telegram API 호출
- 차후 음력을 계산해서 알람을 주는 기능 추가예정