class Complement
    DNAMAP = {
        "G" => "C",
        "C" => "G",
        "T" => "A",
        "A" => "U"
    }
    def self.of_dna(s)
        s.each_char.map{ |c| DNAMAP[c]}.join
    end
    def self.of_rna(s)
    	s.each_char.map{ |c| DNAMAP.key(c)}.join
    end
end
