CREATE SEQUENCE public.tb_user_seq
    INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE public.tb_user (
    id int8 NOT NULL DEFAULT nextval('tb_user_seq'::regclass) PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL,
    user_login VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    role int2 NOT NULL
);

CREATE INDEX idx_user_name ON public.tb_user (user_name);
CREATE INDEX idx_user_email ON public.tb_user (user_email);
CREATE INDEX idx_user_login ON public.tb_user (user_login);
CREATE INDEX idx_user_id ON public.tb_user (id);