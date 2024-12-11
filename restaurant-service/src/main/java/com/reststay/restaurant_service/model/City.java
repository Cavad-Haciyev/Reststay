package com.reststay.restaurant_service.model;
    public enum City {
        ABSERON("Abseron"),
        AGCABADI("Agcabadi"),
        AGDAM("Agdam"),
        AGDAS("Agdas"),
        AGSTAFA("Agstafa"),
        AGSU("Agsu"),
        ASTARA("Astara"),
        BAKI("Bakı"),
        BALAKAN("Balakan"),
        BARDA("Barda"),
        BEYLAQAN("Beylaqan"),
        BILASUVAR("Bilasuvar"),
        CALILABAD("Calilabad"),
        CEBRAYIL("Cəbrayıl"),
        DASKASAN("Daskasan"),
        FUZULI("Fuzuli"),
        GADABAY("Gadabay"),
        GANCA("Ganca"),
        GORANBOY("Goranboy"),
        GOYCAY("Goycay"),
        GOYGOY("Göygöl"),
        HACIQABUL("Haciqabul"),
        IMISLI("Imisli"),
        ISMAYILLI("Ismayilli"),
        KALBACAR("Kalbacar"),
        KURDAMIR("Kurdamir"),
        LACIN("Lacin"),
        LANKARAN("Lankaran"),
        LERIK("Lerik"),
        MASALLI("Masalli"),
        MINGECEVIR("Mingəçevir"),
        NAFTALAN("Naftalan"),
        NAXCIVAN("Naxçıvan"),
        NEFTCALA("Neftçala"),
        OGUZ("Oguz"),
        QABALA("Qabala"),
        QAX("Qax"),
        QAZAX("Qazax"),
        QOBUSTAN("Qobustan"),
        QUBA("Quba"),
        QUBADLI("Qubadli"),
        QUSAR("Qusar"),
        SAATLI("Saatli"),
        SABIRABAD("Sabirabad"),
        SABRAN("Sabran"),
        SAKI("Saki"),
        SALYAN("Salyan"),
        SAMAXI("Samaxi"),
        SAMKIR("Samkir"),
        SAMUX("Samux"),
        SIRVAN("Şirvan"),
        SIYAZAN("Siyazan"),
        SUMQAYIT("Sumqayıt"),
        SUSA("Şuşa"),
        TARTAR("Tərtər"),
        TOVUZ("Tovuz"),
        UCAR("Ucar"),
        XACMAZ("Xaçmaz"),
        XANKANDI("Xankəndi"),
        XIZI("Xızı"),
        XOCALI("Xocalı"),
        XOCAVAND("Xocavənd"),
        YARDIMLI("Yardımlı"),
        YEVLAX("Yevlax"),
        ZANGILAN("Zəngilan"),
        ZAQATALA("Zaqatala"),
        ZARDAB("Zərdab");

        private final String originalName;

        City(String originalName) {
            this.originalName = originalName;
        }

        public String getOriginalName() {
            return originalName;
        }
    }


