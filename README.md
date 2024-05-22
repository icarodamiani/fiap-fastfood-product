# Produto

## Recursos e Bibliotecas
- [x] Java 17
- [x] Document DB
- [x] Spring Boot
- [x]. MapStruct
- [x] Vavr
- [x] JsonPatch


## Dicionário de Linguagem Ubíqua

Termos utilizados na implementação (Presentes em Código)

- **Cliente/Customer**: O consumidor que realiza um pedido no restaurante.
- **Pedido/Order**: A lista de produtos (seja uma bebida, lanche, acompanhamento e/ou sobremesa) realizada pelo cliente no restaurante.
- **Produto/Product**: Item que será consumido pelo cliente, que se enquadra dentro de uma categoria, como por exemplo: bebida, lanche, acompanhamento e/ou sobremesa.
- **Categoria/Product Type**: Como os produtos são dispostos e gerenciados pelo estabelecimento: bebidas, lanches, acompanhamentos e/ou sobremesas.
- **Esteira de Pedidos/Order Tracking**: Responsável pelo andamento e monitoramento do estado do pedido.
- **Funcionário/Employee**: Funcionário do estabelecimento.

## Operações

### [Produto]([BillingController.java](fastfood-api%2Fsrc%2Fmain%2Fjava%2Fio%2Ffiap%2Ffastfood%2Fdriver%2Fcontroller%2Fbilling%2FBillingController.java))
Base única de produtos, não possui vinculo direto com as demais bases. Também, por mais que possua campos que indique a funcionalidade,  nesta versão a aplicação não possui um controle efetivo de estoque um controle de estoque.

As operações de produto são expostas via gRPC e estão descritas no arquivo [fastfood-product.proto](fastfood-product-api%2Fsrc%2Fmain%2Fproto%2Ffastfood-product.proto)

## Início rápido

```shell 
docker-compose up
```
Os serviçõs gRPC são expostos em [localhost:9090](http://localhost:9090).


## Deploy

O deploy pode ser realizado através da execução do pipeline "Deploy product" no Github Actions.
No entanto, anteriormente a execução, faz-se necessária a configuração do ID e SECRET da AWS nos secrets do repositório.
Como o acesso às variáveis e secrets do respositório é limitado ao owner e maintainers, recomendo a execução dos passos do script de deploy localmente com apontamento para a cloud.
Seguem abaixo os passos:

1 -
```
./mvnw -s .m2/settings.xml clean install -Dmaven.test.skip=true -U -P dev
```
2 -
```
docker login registry-1.docker.io
```
3 -
```
aws eks update-kubeconfig --name {CLUSTER_NAME} --region={AWS_REGION}
```
4 -
```
helm upgrade --install fastfood-order charts/fastfood-product \
--kubeconfig /home/runner/.kube/config \
--set containers.image=icarodamiani/fastfood-product \
--set image.tag=latest \
--set database.mongodb.username.value=fastfood \
--set database.mongodb.host.value={AWS_DOCUMENTDB_HOST} \
--set database.mongodb.password.value={AWS_DOCUMENTDB_PASSWORD}
```