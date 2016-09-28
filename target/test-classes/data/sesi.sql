delete from peserta_pelatihan;
delete from sesi;

insert into sesi (id, mulai, sampai, id_materi)
values ('aa','2016-01-02','2016-01-10','aa6');

insert into sesi (id, mulai, sampai, id_materi)
values ('ac','2016-01-08','2016-01-15','aa6');

insert into sesi (id, mulai, sampai, id_materi)
values ('ab','2016-01-07','2016-01-14','aa7');

insert into peserta_pelatihan(id_sesi, id_peserta)
values('aa', 'A001');

insert into peserta_pelatihan(id_sesi, id_peserta)
values('aa', 'A002');

insert into peserta_pelatihan(id_sesi, id_peserta)
values('ab', 'A003');

insert into peserta_pelatihan(id_sesi, id_peserta)
values('ac', 'A002');