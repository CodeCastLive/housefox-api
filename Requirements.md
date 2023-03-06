<style>
    .types {
        display: flex;
        gap: 10px;
    }
    mark {
        padding: 10px;
        width: 100px;
    }
    .item {
        padding: 10px; 
        background-color: cyan;
        color: black;
    }
    dt {
        font-style: normal!important;
        color: darkcyan;
    }

</style>
<h1>Welcome to Housefox Estate Agents 
Backend Architecture !</h1> 
<div>

</div>
HOUSE TABLE

```
id, title, description, location, type, price, currency, rooms, phone, created_date, like_count, view_count
```

<div class="types">
    <mark> Type </mark>
    <span class="item"> Hovli </span>
    <span class="item"> Kvartira </span>
    <span class="item"> Quruq yer </span>
    <span class="item"> Tijorat binosi </span>
</div>
<br />
<div class="types">
    <mark> Currency </mark>
    <span class="item"> DOLLAR </span>
    <span class="item"> EURO </span>
    <span class="item"> SUM </span>
    <span class="item"> RUBL </span>
</div>

<h2>Api List</h2>
<dl>
    <dt>CREATE HOUSE</dt>
    <dd>
        <code>http://localhost:8080/house/create</code>
    </dd>
    <dt>UPDATE HOUSE BY ID</dt>
    <dd>
        <code>http://localhost:8080/house/update/{id}</code>
    </dd>
    <dt>DELETE HOUSE BY ID</dt>
    <dd>
        <code>http://localhost:8080/house/delete/{id}</code>
    </dd>
    <dt>GET HOUSE BY ID</dt>
    <dd>
        <code>http://localhost:8080/house/get/{id}</code>
    </dd>
    <dt>GET ALL HOUSES WITH PAGINATION </dt>
    <dd>
        <code>http://localhost:8080/house/get/all?page=1&size=1</code>
    </dd>
    <dt>GET TOP 10 LIKED HOUSES </dt>
    <dd>
        <code>http://localhost:8080/house/get/top</code>
    </dd>
    <dt>GET TOP 10 VIEWED HOUSES </dt>
    <dd>
        <code>http://localhost:8080/house/get/top</code>
    </dd>
</dl>
<br>
<br>
<hr>
HOUSE TYPE

```
id, house_id, type_uz, type_en, type_ru
```

<div class="types">
    <mark> Like Types </mark>
    <span class="item"> LIKE </span>
    <span class="item"> DISLIKE </span>
</div>

<h2>Api List</h2>
<dl>
    <dt>CREATE LIKE</dt>
    <dd>
        <code>http://localhost:8080/house/like/create</code>
    </dd>
    <dt>CHANGE LIKE STATUS</dt>
    <dd>
        <code>http://localhost:8080/house/like/change</code>
    </dd>
    <dt>DELETE LIKE</dt>
    <dd>
        <code>http://localhost:8080/house/like/delete/{id}</code>
    </dd>
</dl>
<br>

<hr />

HOUSE LIKE

```
id, house_id, profile_id, created_date, type
```

<div class="types">
    <mark> Like Types </mark>
    <span class="item"> LIKE </span>
    <span class="item"> DISLIKE </span>
</div>

<h2>Api List</h2>
<dl>
    <dt>CREATE LIKE</dt>
    <dd>
        <code>http://localhost:8080/house/like/create</code>
    </dd>
    <dt>CHANGE LIKE STATUS</dt>
    <dd>
        <code>http://localhost:8080/house/like/change</code>
    </dd>
    <dt>DELETE LIKE</dt>
    <dd>
        <code>http://localhost:8080/house/like/delete/{id}</code>
    </dd>
</dl>
<br>

<hr />
HOUSE IMAGE

```
id, house_id, attach_id
```

<h2>Api List</h2>
<dl>
    <dt>CREATE IMAGE, VIDEO</dt>
    <dd>
        <code>http://localhost:8080/house/image/create</code>
    </dd>
    <dt>UPDATE IMAGE BY ID</dt>
    <dd>
        <code>http://localhost:8080/house/image/update/{id}</code>
    </dd>
    <dt>DELETE IMAGE BY ID</dt>
    <dd>
        <code>http://localhost:8080/house/image/delete/{id}</code>
    </dd>
   <dt>GET IMAGES BY HOUSE ID</dt>
    <dd>
        <code>http://localhost:8080/house/image/get/by/house/{id}</code>
    </dd>
</dl>

<hr />

<h3>Advatages v2</h3>

1. Currency o'zgarishi ruldan - somga yoki somdan rublga o'zgartirib ko'rish
2. Location bo'yicha o'ziga yaqin yaqin uylarni topib berish
3. Narxlar yordamida qidirish
4. Profile haqida ma'lumotlar yig'ish (<br/>Qanday uylar qidirdi??,<br/> Qaysi tipga man'sub uylar unga ma'qul??,<br/>
   Qanday uylarga like bosdi??,<br/> Shunga qarab recummended list belgilanadi kak youtube!!. Har bir qidirgan ko'rgan
   narsasi <c1ode>profile_history</code> tablega yoziladi)
5. Saytga kirishlar sonini aloxida tableda saqlash (Qayerdan, Qachon...)
6. 