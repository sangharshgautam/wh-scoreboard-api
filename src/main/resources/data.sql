INSERT INTO OMS_USER (id, PROVIDER_USER_ID, email, enabled, DISPLAY_NAME, password, provider, CREATED_DATE, CREATED_BY, MODIFIED_DATE, MODIFIED_BY) VALUES
('fdf18159-40e5-412e-927f-e2a820f1ebe5', '106940445750817892852', 'sangharsh@gmail.com', true, 'Sangharsh Gautam', '$2a$10$7Bk3jZSFsUy4eIqHueFwXuX2bNtWVP0fKCnMLX4pJLh2s8eh9rorq', 'google', '2021-04-14 15:57:55.078248','fdf18159-40e5-412e-927f-e2a820f1ebe5', '2021-04-14 15:57:55.078248','fdf18159-40e5-412e-927f-e2a820f1ebe5');

insert into OMS_ROLE (id, name) VALUES
('05c784cd-ae0e-49f8-9a6f-21450841a754', 'ROLE_USER');


INSERT INTO USER_ROLE(USER_ID, ROLE_ID) VALUES
('fdf18159-40e5-412e-927f-e2a820f1ebe5', '05c784cd-ae0e-49f8-9a6f-21450841a754');

INSERT INTO TEAM( id, name) VALUES
    ('ca9bdbbf-921e-441a-af6d-3a4b1d6c808f', 'TEAM A'),
    ('1d027103-4fc3-4c11-ac3f-fb8f1c51ffe5', 'TEAM B'),
    ('abbf53fc-b92e-4b86-aba7-20a1a2ee5395', 'TEAM C');

INSERT INTO GAME( id, status) VALUES
    ('97769fc3-9fd9-47ee-b9ba-7ed52aa2a7b0', 'FINISHED');
INSERT INTO GAME( id, status) VALUES
    ('afcfef8e-3bc2-4c95-ba4e-1b2ce6f74dce', 'SCHEDULED');
INSERT INTO GAME( id, status) VALUES
    ('60848918-b19f-44f6-a2e3-326261551757', 'SCHEDULED');

INSERT INTO TEAM_SCORE( id, TEAM_ID, GAME_ID, SCORE) VALUES
    ('7b4fa755-0179-4bed-b0ef-6e5c99803b0e', 'ca9bdbbf-921e-441a-af6d-3a4b1d6c808f', '97769fc3-9fd9-47ee-b9ba-7ed52aa2a7b0',  1),
    ('32401851-40ad-41fe-978c-317efe21a1c2', '1d027103-4fc3-4c11-ac3f-fb8f1c51ffe5', '97769fc3-9fd9-47ee-b9ba-7ed52aa2a7b0', 0);

INSERT INTO TEAM_SCORE( id, TEAM_ID, GAME_ID, SCORE) VALUES
     ('fe21eaa4-98be-46c8-aff2-2c46affa2f9f', '1d027103-4fc3-4c11-ac3f-fb8f1c51ffe5', 'afcfef8e-3bc2-4c95-ba4e-1b2ce6f74dce', 0),
     ('7bbfa070-10c9-43cb-9c6b-6320263be8ad', 'abbf53fc-b92e-4b86-aba7-20a1a2ee5395', 'afcfef8e-3bc2-4c95-ba4e-1b2ce6f74dce',  0);

INSERT INTO TEAM_SCORE( id, TEAM_ID, GAME_ID, SCORE) VALUES
     ('aad8498e-cdad-4888-8108-fca445d07bd4', 'abbf53fc-b92e-4b86-aba7-20a1a2ee5395', '60848918-b19f-44f6-a2e3-326261551757', 0),
     ('1b1bcb4e-f5d4-47b0-88dd-80c4ed64894a', 'ca9bdbbf-921e-441a-af6d-3a4b1d6c808f', '60848918-b19f-44f6-a2e3-326261551757',  0);