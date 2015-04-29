class Raindrops

    def convert(n)
        drop = pling(n) << plang(n) << plong(n)
        (n.to_s if drop == "") or drop 
    end

    private

    def pling(n)
        ("Pling" if n % 3 == 0) or ""
    end

    def plang(n)
        ("Plang" if n % 5 == 0) or ""
    end

    def plong(n)
        ("Plong" if n % 7 == 0) or ""
    end

end
