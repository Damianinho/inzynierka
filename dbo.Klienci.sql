SET QUOTED_IDENTIFIER, ANSI_NULLS ON
GO











CREATE view [dbo].[Klienci]
as
select 
k.[ID klienta],
k.[Nazwa klienta],
k.[Nazwa skrocona],
k.Nip,
k.Regon,
k.[ID TPS],
k.TPS,
k.[ID Siec],
k.Siec,
k.[ID Kategoria klienta],
k.[Kategoria klienta],
k.Odbiorca,
k.Dostawca,
k.Platnik,
k.Opis,
k.[Kod zewnetrzny],
k.[ID Status],
k.Status,
k.[ID Czestotliwosc odwiedzin],
k.[Czestotliwosc odwiedzin],
k.[Ilosc tygodni od ostatniej wizyty],
k.[Ilosc dni od ostatniej wizyty],
k.[Poprawnosc odwiedzin],
 -- ADRWIZZAM
k.[Data ostatniej wizyty],
k. [Data ostatniego zamowienia],
k.[Klient odwiedzony],
k.[Licznik wizyt wykonanych],
k.[Licznik wizyt z zamowieniem],
k.[Licznik zamowien],
k.[Suma cena netto z Rab],
k.[Max. Cena netto z Rab],
k.[Srednia cena netto z Rab],
k.[Min. Cena netto z Rab],
k.[Licznik klientow],
k.[GLN],
--Adres
k.[Typ Adresu],
k.[Ulica],
k.[Numer],
k.[Kod pocztowy],
k.Miasto,
k.Kraj,
k.[ID Wojewodztwo],
k.Wojewodztwo,
k.[ID Powiat],
k.Powiat,
k.Dlugosc,
k.Szerokosc,
k.[Szczegoly miejsca dostawy],
k.[Usuniety],
--Finanse 
k.[ID Waluty],
k.Waluta,
k.Rabat,
k.[Limit kredytowy],
k.[Termin platnosci w dniach],
k.[ID Platnik],
k.[ID Sposob platnosci],
k.[Sposob platnosci],
--Umowa
k.[Umowa aktywna], 
k.[Umowa wygasa za 30 dni], 
k.[Umowa wygasła 30 dni temu], 
k.[Umowa wygasła], 
k.[Poprawność odwiedzin algorytm],
--SU/SI 
k.[Data stworzenia] ,
k.[Tworca],
k.[Data ostatniej modifikacji],
k.[Zmiane wykonal],
k.[Kwota ostatniego zamowienia],
k.[Data ostatniej wizyty osobistej],
CASE when daysoff = 1 and DayOfWeekName = 'piątek' then cast(convert(datetime,k."Nastepna wizyta") + 3 as date)
	 when weekend = 1 and DayOfWeekName = 'niedziela' then cast(convert(datetime,k."Nastepna wizyta") + 1 as date)
	 when daysoff = 1 and DayOfWeekName not in ('piątek','czwartek') and k."Nastepna wizyta" like '%12-25' then cast(convert(datetime,k."Nastepna wizyta") + 2 as date)
	 when daysoff = 1 and DayOfWeekName not in ('piątek') then cast(convert(datetime,k."Nastepna wizyta") + 1 as date)
	 when daysoff = 1 and DayOfWeekName  in ('czwartek') and k."Nastepna wizyta" like '%12-25' then cast(convert(datetime,k."Nastepna wizyta") + 4 as date)
	 else "Nastepna wizyta" end as [Data nastepnej wizyty]

from (SELECT  
--Klient
ct.cstid as [ID klienta],
ct.cstName as [Nazwa klienta],
ct.cstShortName as [Nazwa skrocona],
ct.cstNip as Nip,
isnull(ct.cstRegon,'') as Regon,
ct.cstDciCtpsId as [ID TPS],
TPS.dciName as TPS,
ct.cstDciCnetId as [ID Siec],
SIE.dciName as Siec,
ct.cstDciCsctId as [ID Kategoria klienta],
KAT.dciName as [Kategoria klienta],
ct.cstIsBuyer as Odbiorca,
ct.cstIsDealer as Dostawca,
ct.cstIsPayer as Platnik,
isnull(ct.cstDescription,'') as Opis,
isnull(ct.cstExternalCode,'') as [Kod zewnetrzny],
ct.cstDciCstsId as [ID Status],
STA.dciName as Status,
ct.cstDciCfovId as [ID Czestotliwosc odwiedzin],
CZE.dciName as [Czestotliwosc odwiedzin],
DATEDIFF ( week , [Data ostatniej wizyty] ,getdate()) as [Ilosc tygodni od ostatniej wizyty],
DATEDIFF ( day , [Data ostatniej wizyty] ,getdate()) as [Ilosc dni od ostatniej wizyty],
isnull(case when ((ct.cstDciCfovId = 8762203435012037485) and ([Data ostatniej wizyty] > CONVERT(date,  GETDATE()-7 )))then 1 else 
case when ((ct.cstDciCfovId = 8762203435012037486) and ([Data ostatniej wizyty] > CONVERT(date,  GETDATE()-14 )))then 1 else 
case when ((ct.cstDciCfovId = 8762203435012035650) and ([Data ostatniej wizyty] > CONVERT(date,  GETDATE()-21) ))then 1 else 
case when ((ct.cstDciCfovId = 8762203435012032822) and ([Data ostatniej wizyty] > CONVERT(date,   GETDATE()-28 )))then 1 else 
case when ((ct.cstDciCfovId = 581737) and ([Data ostatniej wizyty] > CONVERT(date,  GETDATE()-42 )))then 1 else 
case when ((ct.cstDciCfovId = 581738) and ([Data ostatniej wizyty] >  CONVERT(date,  getdate()-56) ))then 1 else 
case when ((ct.cstDciCfovId = 581739) and ([Data ostatniej wizyty] > CONVERT(date,  GETDATE()-84) ))then 1 else 
case when ((ct.cstDciCfovId = 8762203435012037487) and ([Data ostatniej wizyty] > CONVERT(date,  GETDATE()-168 )))then 1 else 
case when ((ct.cstDciCfovId is null) or ([Data ostatniej wizyty] is null))then 0 else 
 0 end end end end end end end end end , 0) as [Poprawnosc odwiedzin],
 -- ADRWIZZAM
CONVERT(date, wiz.[Data ostatniej wizyty]) as [Data ostatniej wizyty],
case when CZE.dciName  = 'co 24 tygodnie' then CAST(convert(datetime,[Data ostatniej wizyty]) + (24*7) as date)
when CZE.dciName  = 'co 4 tygodnie' then CAST(convert(datetime,[Data ostatniej wizyty]) + (4*7)as date)
when CZE.dciName  = 'co 1 tydzień' then CAST(convert(datetime,[Data ostatniej wizyty]) + (1*7)as date)
when CZE.dciName  = 'co 6 tygodni' then CAST(convert(datetime,[Data ostatniej wizyty]) + (6*7)as date)
when CZE.dciName  = 'co 2 tygodnie' then CAST(convert(datetime,[Data ostatniej wizyty]) + (2*7)as date)
when CZE.dciName  = 'co 12 tygodni' then CAST(convert(datetime,[Data ostatniej wizyty]) + (12*7)as date)
when CZE.dciName  = 'co 3 tygodnie' then CAST(convert(datetime,[Data ostatniej wizyty]) + (3*7)as date)
when CZE.dciName  = 'co 8 tygodni' then CAST(convert(datetime,[Data ostatniej wizyty]) + (8*7)as date) 
end "Nastepna wizyta",
CONVERT(date, zam.[Data ostatniego zamowienia]) as [Data ostatniego zamowienia],
case when CONVERT(date, wiz.[Data ostatniej wizyty]) is null then 0 else 1 end as [Klient odwiedzony],
ADRWIZZAM.[Licznik wizyt wykonanych],
ADRWIZZAM.[Licznik wizyt z zamowieniem],
ADRWIZZAM.[Licznik zamowien],
ADRWIZZAM.[Suma cena netto z Rab],
ADRWIZZAM.[Max. Cena netto z Rab],
CAST(ADRWIZZAM.[Srednia cena netto z Rab] AS DECIMAL(18,2)) as [Srednia cena netto z Rab],
ADRWIZZAM.[Min. Cena netto z Rab],
1 as [Licznik klientow],
isnull(ct.cstGLN,'') as [GLN],
--Adres
isnull(ct.cstAddressPrefix,'') as [Typ Adresu],
ct.cstAddressStreet as [Ulica],
ct.cstAddressNumber as [Numer],
ct.cstAddressZipCode as [Kod pocztowy],
ct.cstAddressCity as Miasto,
isnull(ct.cstAddressCountry,'') as Kraj,
ct.cstDciCadaId as [ID Wojewodztwo],
WOJ.dciName as Wojewodztwo,
ct.cstDciCada2Id as [ID Powiat],
POW.dciName as Powiat,
ct.cstPosX as Dlugosc,
ct.cstPosY as Szerokosc,
ct.cstDeliveryAddress as [Szczegoly miejsca dostawy],
ct.cstDeleted as [Usuniety],
--Finanse 
ct.cstDciCcurId as [ID Waluty],
WAL.dciName as Waluta,
isnull(ct.cstDefaultRebate, 0) as Rabat,
isnull(ct.cstDefaultCreditLimit,0) as [Limit kredytowy],
isnull(ct.cstDefaultPayTime,0) as [Termin platnosci w dniach],
isnull(ct.cstCstPayId,'') as [ID Platnik],
isnull(ct.cstDciCtopId,'') as [ID Sposob platnosci],
isnull(SPO.dciName,'') as [Sposob platnosci],
--Umowa
case when CONVERT(date,  ENTDZ.atlValue, 120) is null then 0 else
case 
when  CONVERT(date,  GETDATE()) <= CONVERT(Date,  ENTDZ.atlValue)then 1 
else 0 end end as [Umowa aktywna], 
case when CONVERT(date,  ENTDZ.atlValue, 120) is null then 0 else
case 
when CONVERT(Date,  ENTDZ.atlValue)between getdate() and getdate()+30 then 1 
else 0 end end as [Umowa wygasa za 30 dni], 
case when CONVERT(date,  ENTDZ.atlValue, 120) is null then 0 else
case 
when CONVERT(Date,  ENTDZ.atlValue)between getdate()-30 and getdate() then 1 
else 0 end end as [Umowa wygasła 30 dni temu], 
case when CONVERT(date,  ENTDZ.atlValue, 120) is null then 0 else
case 
when  CONVERT(date,  GETDATE()) >= CONVERT(Date,  ENTDZ.atlValue)then 1 
else 0 end end as [Umowa wygasła], 
pok.[Poprawność odwiedzin] as [Poprawność odwiedzin algorytm],
--SU/SI 
ct.auditCD as [Data stworzenia] ,
ct.auditCU as [Tworca],
isnull(ct.auditMD,'') as [Data ostatniej modifikacji],
isnull(ct.auditMU,'') as [Zmiane wykonal],
[Sum Cena netto z Rab] as [Kwota ostatniego zamowienia],
OsobWiz.[Data ostatniej wizyty osobistej]
  FROM Customer ct
  left join EntDictionaryItem TPS on ct.cstDciCtpsId = TPS.dciId -- TPS
  left join EntDictionaryItem WOJ on ct.cstDciCadaId = WOJ.dciId -- Wojewodztwo
  left join EntDictionaryItem POW on ct.cstDciCada2Id = POW.dciId -- Powiat
  left join EntDictionaryItem STA on ct.cstDciCstsId = STA.dciId -- Status
  left join EntDictionaryItem WAL on ct.cstDciCcurId = WAL.dciId -- Waluta  
  left join EntDictionaryItem SIE on ct.cstDciCnetId = SIE.dciId -- Siec  
  left join EntDictionaryItem SPO on ct.cstDciCtopId = SPO.dciId -- Sposob platnosci 
  left join EntDictionaryItem KAT on ct.cstDciCsctId = KAT.dciId -- Kategoria klienta
  left join EntDictionaryItem CZE on ct.cstDciCfovId = CZE.dciId -- Częstotliwosc odwiedzin
  left join
(
select[ID klienta], max([Data rozpoczecia wizyty]) as [Data ostatniej wizyty osobistej]  from wizyty 
where (([Typ wizyty] like '%osob%') or ([Typ wizyty] like '%Potencjalny%'))
group by [ID klienta])OsobWiz
on OsobWiz.[ID klienta] = ct.cstId
  left join (
  --co 24 tygodnie 2.167
--NULL --błąd
--co 4 tygodnie 13
--co 1 tydzień 52
--co 6 tygodni 8.67
--co 2 tygodnie 26
--co 12 tygodni 4.33
--co 3 tygodnie 17.33
--co 8 tygodni 6.5
select *,
case 
when cast ([Ilość wizyt] as int) >= cast ([Przelicznik na tydzien] as int) then 1 
else 0 end as [Poprawność odwiedzin] 
from 
(select 
kli.cstid,
isnull(x.[Ilość wizyt],0) as [Ilość wizyt],
CZE.dciName,
case 
when CZE.dciName = 'co 24 tygodnie' then 2.167
when CZE.dciName = 'co 4 tygodnie' then 13
when CZE.dciName = 'co 1 tydzień' then 52
when CZE.dciName = 'co 6 tygodni' then 8.67
when CZE.dciName = 'co 2 tygodnie' then 26
when CZE.dciName = 'co 12 tygodni' then 4.33
when CZE.dciName = 'co 3 tygodnie' then 17.33
when CZE.dciName = 'co 8 tygodni' then 6.5 
end as [Przelicznik na tydzien]
from Customer kli 
left join EntDictionaryItem CZE on kli.cstDciCfovId = CZE.dciId -- Częstotliwosc odwiedzin
left join (
select [ID klienta], count([id wizyty]) as [Ilość wizyt] from wizyty wiz
where data > getdate()-365
and [ID typ wizyty] not in (
8762203435012037587, --Rozpoczęcie dnia pracy
8762203435012037465, --Praca biurowa
8762203435012037589, --Zakończenie dnia pracy
581798, --Aktualizacja klienta poza wizytą
580993,--	Ankieta poza wizytą
8762203435012037588	--Przerwa w pracy
)
group by [ID klienta]) x 
on kli.cstid = x.[ID klienta]
)tab 
  ) pok on ct.cstid = pok.cstid -- poprawnośc odwiedzin
  left join EntAttributeLink ENTDZ on ct.cstid = ENTDZ.atlObjectId and ENTDZ.atlAtrId = 8762203435012031633 and ENTDZ.atlDeleted is null -- Data zakonczenia umowy
  left join (select zam.[ID klienta], zam2.[Data ostatniego zamowienia], zam.[Cena netto z Rab] as  [Sum Cena netto z Rab]  from Zamowienia zam join (
select [ID klienta], max([Data zamowienia]) as [Data ostatniego zamowienia]
from Zamowienia group by [ID klienta]) zam2 on zam.[ID klienta] = zam2.[ID klienta] and zam.[Data zamowienia] = zam2.[Data ostatniego zamowienia]
) zam on ct.cstid = zam.[ID klienta]
  left join (select [ID klienta], max([Data rozpoczecia wizyty]) as [Data ostatniej wizyty] from Wizyty
where [ID typ wizyty] not in (
8762203435012037587, --Rozpoczęcie dnia pracy
8762203435012037465, --Praca biurowa
8762203435012037589, --Zakończenie dnia pracy
581798, --Aktualizacja klienta poza wizytą
580993,--	Ankieta poza wizytą
8762203435012037588	--Przerwa w pracy
)
group by [ID klienta]) wiz on ct.cstid = wiz.[ID klienta]
Left Join
(Select 
wiz.[ID klienta],
sum(wiz.[Licznik wizyt wykonanych]) as [Licznik wizyt wykonanych],
sum(wiz.[Licznik zamowienie]) as [Licznik wizyt z zamowieniem],
ZamCou.[Licznik zamowien],
ZamSUM.[Suma cena netto z Rab],
ZamMax.[Max. Cena netto z Rab],
ZamAVG.[Srednia cena netto z Rab],
ZamMin.[Min. Cena netto z Rab]
From Wizyty Wiz
Left join
(select [ID klienta], max([Cena netto z Rab]) as [Max. Cena netto z Rab] from Zamowienia
group by [ID klienta]) ZamMax
on Wiz.[ID klienta] = ZamMax.[ID klienta]
Left join
(select [ID klienta], sum([Licznik zamowien]) as [Licznik zamowien] from Zamowienia
group by [ID klienta]) ZamCou
on Wiz.[ID klienta] = ZamCou.[ID klienta]
Left join
(select [ID klienta], AVG([Cena netto z Rab]) as [Srednia cena netto z Rab] from Zamowienia
group by [ID klienta]) ZamAVG
on Wiz.[ID klienta] = ZamAVG.[ID klienta]
Left join
(select [ID klienta], Sum([Cena netto z Rab]) as [Suma cena netto z Rab] from Zamowienia
group by [ID klienta]) ZamSUM
on Wiz.[ID klienta] = ZamSUM.[ID klienta]
Left join
(select [ID klienta], min([Cena netto z Rab]) as [Min. Cena netto z Rab] from Zamowienia
group by [ID klienta]) ZamMin
on Wiz.[ID klienta] = ZamMin.[ID klienta]
where Wiz.[Licznik wizyt wykonanych] = 1
group by wiz.[ID klienta], ZamCou.[Licznik zamowien], ZamMax.[Max. Cena netto z Rab], ZamAVG.[Srednia cena netto z Rab], ZamMin.[Min. Cena netto z Rab], ZamSUM.[Suma cena netto z Rab]
) ADRWIZZAM
on ct.cstId = ADRWIZZAM.[ID klienta]) k
left join DimDate on k."Nastepna wizyta" = DimDate.DWDateKey

GO