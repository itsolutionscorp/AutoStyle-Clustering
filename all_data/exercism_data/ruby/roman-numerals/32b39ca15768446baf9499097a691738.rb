class Fixnum
    def to_roman
        ms = self.divmod(1000)[0]
        ds = self.divmod(1000)[1].divmod(500)[0]
        cs = self.divmod(1000)[1].divmod(500)[1].divmod(100)[0]
        ls = self.divmod(1000)[1].divmod(500)[1].divmod(100)[1].divmod(50)[0]
        xs = self.divmod(1000)[1].divmod(500)[1].divmod(100)[1].divmod(50)[1].divmod(10)[0]
        vs = self.divmod(1000)[1].divmod(500)[1].divmod(100)[1].divmod(50)[1].divmod(10)[1].divmod(5)[0]
        is = self.divmod(1000)[1].divmod(500)[1].divmod(100)[1].divmod(50)[1].divmod(10)[1].divmod(5)[1]
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
