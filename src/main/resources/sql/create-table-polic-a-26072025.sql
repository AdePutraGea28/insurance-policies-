CREATE TABLE public."policy" (
	id serial4 NOT NULL,
	holder_name varchar(255) NOT NULL,
	"type" varchar(100) NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	premium_amount numeric(15, 2) NOT NULL,
	created_by varchar(25) NULL,
	updated_by varchar(25) NULL,
	created_date timestamp NOT NULL,
	updated_date timestamp NOT NULL,
	CONSTRAINT policy_pkey PRIMARY KEY (id)
);