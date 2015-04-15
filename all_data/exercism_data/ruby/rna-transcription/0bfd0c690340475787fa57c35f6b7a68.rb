class Complement
    
    def self.of_dna(rna)
        dna = ''
        rna.chars.each_with_index do |nucleotide|
            case
            when nucleotide == 'G'
                dna = dna + 'C'
            when nucleotide == 'C'
                dna = dna + 'G'
            when nucleotide == 'T'
                dna = dna + 'A'
            when nucleotide == 'A'
                dna = dna + 'U'
            end
        end
        return dna
    end
    
    def self.of_rna(dna)
        rna = ''
        dna.chars.each_with_index do |nucleotide|
            case
            when nucleotide == 'G'
                rna = rna + 'C'
            when nucleotide == 'C'
                rna = rna + 'G'
            when nucleotide == 'A'
                rna = rna + 'T'
            when nucleotide == 'U'
                rna = rna + 'A'
            end
        end
        return rna
    end
    
end
