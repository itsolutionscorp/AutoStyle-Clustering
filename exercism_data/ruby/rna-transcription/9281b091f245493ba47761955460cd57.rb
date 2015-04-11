class Complement
    def self.of_dna(strand)
        dna = {
            "G" => "C",
            "C" => "G",
            "T" => "A",
            "A" => "U"
        }

        rna_strand = ""
        strand.each_char do |c|
            rna_strand.concat( dna[c] )
        end

        rna_strand
    end

    def self.of_rna(strand)
        rna = {
            "C" => "G",
            "G" => "C",
            "A" => "T",
            "U" => "A"
        }

        dna_strand = ""
        strand.each_char do |c|
            dna_strand.concat( rna[c] )
        end

        dna_strand
    end
end
