class Fixnum
    def to_roman
        ms = self.divmod(1000)[0]
        ds = self.divmod(1000)[1].divmod(500)[0]
        cs = self.divmod(1000)[1].divmod(500)[1].divmod(100)[0]
        ls = self.divmod(1000)[1].divmod(500)[1].divmod(100)[1].divmod(50)[0]
        xs = self.divmod(1000)[1].divmod(500)[1].divmod(100)[1].divmod(50)[1].divmod(10)[0]
        vs = self.divmod(1000)[1].divmod(500)[1].divmod(100)[1].divmod(50)[1].divmod(10)[1].divmod(5)[0]
        is = self.divmod(1000)[1].divmod(500)[1].divmod(100)[1].divmod(50)[1].divmod(10)[1].divmod(5)[1]
        out = "M" * ms + ((ds==1 and cs==4) ? "CM" : "D" * ds + ((cs==4) ? "CD" : "C" * cs))
        out += (ls==1 and xs==4) ? "XC" : "L" * ls + ((xs==4) ? "XL" :  "X" * xs)
        out += (vs==1 and is==4) ? "IX" : "V" * vs + ((is==4) ? "IV" : "I" * is)
        out
    end
end
