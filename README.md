# rabbitmq-practice
learning about queues with rabbitmq

Execute o rabbitmq server 

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management

Com o servidor ligado, experimente enviar uma mensagem utilizando a classe Send.

Após isso, experimente Receber a mensagem executando a Classe Recv.

Podes executar inumeras instancias da classe Recv para serem executas, porém quando a mensagem é enviada, somente uma dessas instancias lerá a mensagem.
