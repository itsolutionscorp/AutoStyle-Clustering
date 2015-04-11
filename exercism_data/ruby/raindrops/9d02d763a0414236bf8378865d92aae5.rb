class Raindrops
    DROPS = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    def self.convert(n)
        out = DROPS.reduce("") {|s,(key, val)| s + ((n%key).zero? ? val : "")} || n.to_s
        out.empty? ? n.to_s : out
    end
end
