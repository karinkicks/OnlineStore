--
-- PostgreSQL database dump
--

-- Dumped from database version 11.11
-- Dumped by pg_dump version 11.11

-- Started on 2021-03-20 23:51:02

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
-- TOC entry 196 (class 1259 OID 16394)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    name text NOT NULL,
    cost bigint NOT NULL
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16404)
-- Name: product_cost_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_cost_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_cost_seq OWNER TO postgres;

--
-- TOC entry 2833 (class 0 OID 0)
-- Dependencies: 197
-- Name: product_cost_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_cost_seq OWNED BY public.product.cost;


--
-- TOC entry 2700 (class 2604 OID 16406)
-- Name: product cost; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN cost SET DEFAULT nextval('public.product_cost_seq'::regclass);


--
-- TOC entry 2826 (class 0 OID 16394)
-- Dependencies: 196
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, name, cost) FROM stdin;
1	product1	10
2	product2	20
3	product3	30
4	product4	40
5	product5	50
6	product6	60
\.


--
-- TOC entry 2834 (class 0 OID 0)
-- Dependencies: 197
-- Name: product_cost_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_cost_seq', 1, false);


--
-- TOC entry 2702 (class 2606 OID 16403)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2704 (class 2606 OID 16450)
-- Name: product uq_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT uq_id UNIQUE (id) INCLUDE (id);


-- Completed on 2021-03-20 23:51:02

--
-- PostgreSQL database dump complete
--

