--
-- PostgreSQL database dump
--

-- Dumped from database version 11.11
-- Dumped by pg_dump version 11.11

-- Started on 2021-03-20 23:50:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 203 (class 1259 OID 16431)
-- Name: person2product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person2product (
    id bigint NOT NULL,
    person_id bigint NOT NULL,
    product_id bigint NOT NULL,
    product_cost bigint NOT NULL
);


ALTER TABLE public.person2product OWNER TO postgres;

--
-- TOC entry 2837 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN person2product.product_cost; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.person2product.product_cost IS ' сколько стоил товар в момент покупки клиентом';


--
-- TOC entry 200 (class 1259 OID 16425)
-- Name: person2product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person2product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person2product_id_seq OWNER TO postgres;

--
-- TOC entry 2838 (class 0 OID 0)
-- Dependencies: 200
-- Name: person2product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person2product_id_seq OWNED BY public.person2product.id;


--
-- TOC entry 201 (class 1259 OID 16427)
-- Name: person2product_person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person2product_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person2product_person_id_seq OWNER TO postgres;

--
-- TOC entry 2839 (class 0 OID 0)
-- Dependencies: 201
-- Name: person2product_person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person2product_person_id_seq OWNED BY public.person2product.person_id;


--
-- TOC entry 202 (class 1259 OID 16429)
-- Name: person2product_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person2product_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person2product_product_id_seq OWNER TO postgres;

--
-- TOC entry 2840 (class 0 OID 0)
-- Dependencies: 202
-- Name: person2product_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person2product_product_id_seq OWNED BY public.person2product.product_id;


--
-- TOC entry 2700 (class 2604 OID 16434)
-- Name: person2product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person2product ALTER COLUMN id SET DEFAULT nextval('public.person2product_id_seq'::regclass);


--
-- TOC entry 2701 (class 2604 OID 16435)
-- Name: person2product person_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person2product ALTER COLUMN person_id SET DEFAULT nextval('public.person2product_person_id_seq'::regclass);


--
-- TOC entry 2702 (class 2604 OID 16436)
-- Name: person2product product_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person2product ALTER COLUMN product_id SET DEFAULT nextval('public.person2product_product_id_seq'::regclass);


--
-- TOC entry 2831 (class 0 OID 16431)
-- Dependencies: 203
-- Data for Name: person2product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person2product (id, person_id, product_id, product_cost) FROM stdin;
1	1	1	10
2	1	2	20
3	2	3	30
4	2	4	40
5	4	1	10
6	4	5	50
\.


--
-- TOC entry 2841 (class 0 OID 0)
-- Dependencies: 200
-- Name: person2product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person2product_id_seq', 1, false);


--
-- TOC entry 2842 (class 0 OID 0)
-- Dependencies: 201
-- Name: person2product_person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person2product_person_id_seq', 1, false);


--
-- TOC entry 2843 (class 0 OID 0)
-- Dependencies: 202
-- Name: person2product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person2product_product_id_seq', 1, false);


--
-- TOC entry 2704 (class 2606 OID 16438)
-- Name: person2product person2product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person2product
    ADD CONSTRAINT person2product_pkey PRIMARY KEY (id);


--
-- TOC entry 2706 (class 2606 OID 16444)
-- Name: person2product person_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person2product
    ADD CONSTRAINT person_id FOREIGN KEY (person_id) REFERENCES public.person(id) NOT VALID;


--
-- TOC entry 2705 (class 2606 OID 16439)
-- Name: person2product product_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person2product
    ADD CONSTRAINT product_id FOREIGN KEY (product_id) REFERENCES public.product(id) NOT VALID;


-- Completed on 2021-03-20 23:50:48

--
-- PostgreSQL database dump complete
--

