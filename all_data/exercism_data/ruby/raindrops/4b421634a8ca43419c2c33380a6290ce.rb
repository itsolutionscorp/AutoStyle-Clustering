class Raindrops
    def self.convert(n)
        translate = { "3" => "Pling", "5" => "Plang", "7" => "Plong" }
        factors = factorize n
        say = ""
        factors.each { |f| say << translate[f.to_s] if translate[f.to_s] }
        return say unless say == ""
        return n.to_s
    end

    private
    def self.factorize(n)
        factors = []
        cur_factor = 2
        remaining = n
        while remaining > 1
            if remaining % cur_factor == 0
                factors << cur_factor
                # avoid duplicates by ignoring here
                while remaining % cur_factor == 0
                    remaining /= cur_factor
                end
            end
            cur_factor += 1
        end
        factors
    end
end
