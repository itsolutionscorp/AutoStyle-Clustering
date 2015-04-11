class Raindrops

    def convert(n)
        drop = pling(n) << plang(n) << plong(n)
        (n.to_s if drop == "") or drop 
    end

    private

    def text_for_factor(text, factor, n)
        (text if n % factor == 0) or ""
    end

    def pling(n)
        text_for_factor "Pling", 3, n
    end

    def plang(n)
        text_for_factor "Plang", 5, n
    end

    def plong(n)
        text_for_factor "Plong", 7, n
    end

end
