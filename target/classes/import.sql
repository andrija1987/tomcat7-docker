CREATE DATABASE bugi_tracker DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

insert into zaposleni (id, ime, priimek, upo_ime, upo_geslo, role) values (default, 'Bojan', 'Bajc', 'bojan', 'ae404a1ecbcdc8e96ae4457790025f50', 'ROLE_USER');
insert into zaposleni (id, ime, priimek, upo_ime, upo_geslo, role) values (default, 'Sašo', 'Robida', 'saso', 'ae404a1ecbcdc8e96ae4457790025f50', 'ROLE_USER');
insert into zaposleni (id, ime, priimek, upo_ime, upo_geslo, role) values (default, 'Tomaž', 'Furlan', 'tomaz', 'ae404a1ecbcdc8e96ae4457790025f50', 'ROLE_USER');
insert into zaposleni (id, ime, priimek, upo_ime, upo_geslo, role) values (default, 'Davor', 'Tavèar', 'davor', 'ae404a1ecbcdc8e96ae4457790025f50', 'ROLE_USER');
insert into zaposleni (id, ime, priimek, upo_ime, upo_geslo, role) values (default, 'Jurij', 'Rejec', 'jurij', 'ae404a1ecbcdc8e96ae4457790025f50', 'ROLE_USER');
insert into zaposleni (id, ime, priimek, upo_ime, upo_geslo, role) values (default, 'Špela', 'Vitamvas', 'spela', 'ae404a1ecbcdc8e96ae4457790025f50', 'ROLE_USER');
insert into zaposleni (id, ime, priimek, upo_ime, upo_geslo, role) values (default, 'Aleš', 'Muha', 'ales', 'ae404a1ecbcdc8e96ae4457790025f50', 'ROLE_USER');
insert into zaposleni (id, ime, priimek, upo_ime, upo_geslo, role) values (default, 'Igor', 'Panjan', 'igor', 'ae404a1ecbcdc8e96ae4457790025f50', 'ROLE_USER');
insert into zaposleni (id, ime, priimek, upo_ime, upo_geslo, role) values (default, 'Miha', 'Bogataj', 'miha', '4ec5c2b258d1c0773dbce325826182f9', 'ROLE_ADMIN');

insert into odsotnost_razlog (id, naziv, barva) values(default, 'Bolniška', '74FEF8');
insert into odsotnost_razlog (id, naziv, barva) values(default, 'Dopust', '33CC33');
insert into odsotnost_razlog (id, naziv, barva) values(default, 'Delo od doma', 'FFFF33');
insert into odsotnost_razlog (id, naziv, barva) values(default, 'Delo na terenu', 'FF2626');
insert into odsotnost_razlog (id, naziv, barva) values(default, 'Drugo', '000000');