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
            ds = 0
            cs = 0
        end
        out += "D" * ds
        if cs==4
            out += "CD"
            cs = 0
        end
        out += "C" * cs
        if ls==1 and xs==4
            out += "XC"
            ls = 0
            xs = 0
        end
        out += "L" * ls
        if xs==4
            out += "XL"
            xs = 0
        end
        out += "X" * xs
        if vs==1 and is==4
            out += "IX"
            vs = 0
            is = 0
        end
        out += "V" * vs
        if is==4
            out += "IV"
            is = 0
        end
        out += "I" * is
        return out
    end
end
