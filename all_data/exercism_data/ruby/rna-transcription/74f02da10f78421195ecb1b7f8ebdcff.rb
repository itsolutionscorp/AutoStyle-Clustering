class Complement
    @dnaMap = {
        "G" => "C",
        "C" => "G",
        "T" => "A",
        "A" => "U"
    }
    def self.of_dna(s)
        out = ""
        s.each_char do |c|
            out << @dnaMap[c]
        end
        out
    end
    def self.of_rna(s)
        out = ""
        s.each_char do |c|
            out << @dnaMap.key(c)
        end
        out
    end
end
