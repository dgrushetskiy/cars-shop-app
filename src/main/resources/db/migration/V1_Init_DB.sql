CREATE SCHEMA IF NOT EXISTS cars AUTHORIZATION root;
CREATE SCHEMA IF NOT EXISTS batch_schema AUTHORIZATION root;

CREATE  TABLE cars.company (
                               id                   bigserial  NOT NULL  ,
                               created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                               updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                               status     boolean   DEFAULT false             NOT NULL,
                               name                 varchar(150)  NOT NULL  ,
                               CONSTRAINT pk_company PRIMARY KEY ( id )
);

CREATE  TABLE cars.role_company (
                                    id                   bigserial  NOT NULL  ,
                                    created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                    updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                    status     boolean   DEFAULT false             NOT NULL,
                                    name                 varchar(100)  NOT NULL  ,
                                    CONSTRAINT pk_role_company PRIMARY KEY ( id )
);

CREATE  TABLE cars.type_car (
                                id                   bigserial  NOT NULL  ,
                                created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                status     boolean   DEFAULT false             NOT NULL,
                                type_name            varchar(100)  NOT NULL  ,
                                CONSTRAINT pk_type_car PRIMARY KEY ( id )
);

CREATE  TABLE cars.person (
                              id                   bigserial  NOT NULL  ,
                              created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                              updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                              status     boolean   DEFAULT false             NOT NULL,
                              first_name           varchar(150)  NOT NULL  ,
                              last_name            varchar(150)  NOT NULL  ,
                              salary               decimal(10,2)  NOT NULL  ,
                              company_id           bigint  NOT NULL  ,
                              role_company_id      bigint  NOT NULL  ,
                              CONSTRAINT pk_person PRIMARY KEY ( id )
);

CREATE  TABLE cars.car (
                           id                   bigserial  NOT NULL  ,
                           created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                           updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                           status     boolean   DEFAULT false             NOT NULL,
                           name                 varchar(100)  NOT NULL  ,
                           number_car           varchar(10)  NOT NULL  ,
                           type_id              bigint  NOT NULL  ,
                           person_id            bigint  NOT NULL  ,
                           CONSTRAINT pk_car PRIMARY KEY ( id )
);

ALTER TABLE cars.car ADD CONSTRAINT fk_car_type_car FOREIGN KEY ( type_id ) REFERENCES cars.type_car( id ) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE cars.car ADD CONSTRAINT fk_car_person FOREIGN KEY ( person_id ) REFERENCES cars.person( id ) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE cars.person ADD CONSTRAINT fk_person_company FOREIGN KEY ( company_id ) REFERENCES cars.company( id ) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE cars.person ADD CONSTRAINT fk_person_role_company FOREIGN KEY ( role_company_id ) REFERENCES cars.role_company( id ) ON DELETE CASCADE ON UPDATE RESTRICT;

COMMENT ON COLUMN cars.company.name IS 'название компании';

COMMENT ON COLUMN cars.type_car.type_name IS 'название';

COMMENT ON COLUMN cars.person.first_name IS 'имя пользователя';

COMMENT ON COLUMN cars.person.last_name IS 'фамилия сотрудника';

COMMENT ON COLUMN cars.person.salary IS 'зарплата';

COMMENT ON TABLE cars.car IS 'машина';

COMMENT ON COLUMN cars.car.number_car IS 'номер машины';

COMMENT ON COLUMN cars.car.type_id IS 'связь с типом машины';
