class Fixnum
    def numberOf(x)
        (self-(self % x)) / x
    end

    def to_roman
        n = self
        ms = n.numberOf(1000)
        n = n % 1000
        ds = n.numberOf(500)
        n = n % 500
        cs = n.numberOf(100)
        n = n % 100
        ls = n.numberOf(50)
        n = n % 50
        xs = n.numberOf(10)
        n = n % 10
        vs = n.numberOf(5)
        is = n % 5
        out = "M" * ms
        if ds==1 and cs==4
            out += "CM"
            cs = 0
        else
            out += "D" * ds
        end
        out += (cs==4) ? "CD" : "C" * cs
        if ls==1 and xs==4
            out += "XC"
            xs = 0
        else
            out += "L" * ls
        end
        out += (xs==4) ? "XL" :  "X" * xs
        if vs==1 and is==4
            out += "IX"
            is = 0
        else
            out += "V" * vs
        end
        out += (is==4) ? "IV" : "I" * is
        out
    end
end
