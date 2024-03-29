use lem;
drop table if exists `equipment`;
create table equipment
(
    equipment_id    bigint auto_increment
        primary key,
    name            varchar(50)          not null,
    model           varchar(255)         null,
    serial_number   varchar(100)         null,
    production_date date                 null,
    purchase_date   date                 null,
    supplier_id     bigint               null,
    status          smallint   default 1 not null,
    description     varchar(256)         null,
    remark          varchar(512)         null comment '备注',
    creator_id      bigint               null comment '创建者ID',
    create_time     datetime             null comment '创建时间',
    updater_id      bigint               null comment '更新者ID',
    update_time     datetime             null comment '更新时间',
    deleted         tinyint(1) default 0 not null comment '逻辑删除'
);

-- 供应商表 (supplier)
drop table if exists supplier;
create table supplier
(
    supplier_id     bigint auto_increment
        primary key,
    name            varchar(255)         not null,
    contact_person  varchar(100)         null,
    contact_phone   varchar(18)          null,
    contact_address varchar(255)         null,
    status          smallint   default 1 not null,
    remark          varchar(512)         null comment '备注',
    creator_id      bigint               null comment '创建者ID',
    create_time     datetime             null comment '创建时间',
    updater_id      bigint               null comment '更新者ID',
    update_time     datetime             null comment '更新时间',
    deleted         tinyint(1) default 0 not null comment '逻辑删除'
);


-- 设备预约表 (equipment_reservation)
drop table if exists `equipment_reservation`;
create table equipment_reservation
(
    reservation_id bigint auto_increment primary key,
    equipment_id   bigint               not null comment '设备ID',
    start_datetime datetime             not null comment '开始时间',
    end_datetime   datetime             not null comment '结束时间',
    status         smallint   default 1 not null comment '状态 1:等待 2:同意 3:拒绝 0:取消',
    remark        varchar(512) null comment '预约备注',
    creator_id     bigint               null comment '创建者ID',
    create_time    datetime             null comment '创建时间',
    updater_id     bigint               null comment '更新者ID',
    update_time    datetime             null comment '更新时间',
    review_remark varchar(512) null comment '审批备注',
    deleted        tinyint(1) default 0 not null comment '逻辑删除'
);


-- 设备维修保养记录表 (maintenance_record)
drop table if exists `maintenance_record`;
create table maintenance_record
(
    maintenance_record_id bigint auto_increment
        primary key,
    equipment_id          bigint               null,
    technician_id         bigint               null,
    maintenance_date      datetime             null,
    maintenance_content   text                 null,
    status                smallint   default 1 not null comment '状态 1:未开始 2:维修中 3:已维修 4:维修失败',
    remark                varchar(512)         null comment '备注',
    creator_id            bigint               null comment '创建者ID',
    create_time           datetime             null comment '创建时间',
    updater_id            bigint               null comment '更新者ID',
    update_time           datetime             null comment '更新时间',
    deleted               tinyint(1) default 0 not null comment '逻辑删除'
);

