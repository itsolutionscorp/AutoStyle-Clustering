class Complement
    @@dnaToRna = {
        "G" => "C",
        "C" => "G",
        "T" => "A",
        "A" => "U"
    }

    @@rnaToDna = {
        "C" => "G",
        "G" => "C",
        "A" => "T",
        "U" => "A"
    }

    def self.of_dna(dna)
        return dna.chars.map { |ch|
            if @@dnaToRna[ch]
                @@dnaToRna[ch]
            else
                ch
            end
        }.join("")
    end

    def self.of_rna(rna)
        return rna.chars.map { |ch|
            if @@rnaToDna[ch]
                @@rnaToDna[ch]
            else
                ch
            end
        }.join("")
    end
end
