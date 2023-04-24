## Requirement
https://docs.google.com/document/d/1GBvrJO5hdIXWlU6hdJ6qvduoK1JnVGjgvvvOQ8XR_HE/edit#
## API Reference

#### Get Account

```http
  GET /api/v1/account/{id}
```

| Parameter | Type    |Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | *NotNull*. `AccountId` |

#### Create Account

```http
  POST /api/v1/account
```

| Parameter | Type | Description | constrain |
| :-------- | :--- | :---------- | :---------|
| `customerId field`      | `Long` | `customerId ` |`NotNull`, `positive`  |
| `accountLimit` | `Double` | `accountLimit` |`NotNull`, `positive`  |
| `perTransactionLimit`|`Double` |`perTransactionLimit` |`NotNull`, `positive`  |

#### Create LimitOffer

```http
  POST /api/v1/limitOffer
```
| Parameter | Type | Description | constrain |
| :-------- | :--- | :---------- | :---------|
|`accountId`|`Long`|  `accountId`| `NotNull`, `positive`|
|`limitType`|`ACCOUNT_LIMIT`,`PER_TRANSACTION_LIMIT`|`type of offer to create`|`NonNull`|
|`newLimit`|`double`| `newLimit to be updated`|`NotNull`, `positive`|
|`offerActivationTime`|`LocalDateTime`|`offer activation time`|`NonNull`|
|`offerExpiryTime`|`LocalDateTime`|`offer expiry time`|`NonNull`|

#### Get LimitOffers

```http
  GET /api/v1/limitOffer
```
| Parameter | Type | Description | constrain |
| :-------- | :--- | :---------- | :---------|
|`accountId`|`Long`|`account id`|`NotNull`, `positive`|
|`activeDate`|`LocalDate`|'date from which to check`|`optional`| 

#### Update LimitOffer

```http
  PUT /api/v1/limitOffer
```
| Parameter | Type | Description | constrain |
| :-------- | :--- | :---------- | :---------|
|`limitOfferId`|`Long`|`limitOfferId`|`NotNull`, `positive`|
|`status`|`PENDING`,`ACCEPTED`,`REJECTED`|`status`|`NonNull`|
