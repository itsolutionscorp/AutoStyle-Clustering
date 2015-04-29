class Complement
    def self.get_dna_complement_of(nucleotide)
        if nucleotide == 'G'
           return 'C'
        elsif nucleotide == 'C'
           return 'G'
        elsif nucleotide == 'T'
           return 'A'
        elsif nucleotide == 'A'
           return 'U'
        end 
    end
    def self.get_rna_complement_of(nucleotide)
        if nucleotide == 'C'
           return 'G'
        elsif nucleotide == 'G'
           return 'C'
        elsif nucleotide == 'A'
           return 'T'
        elsif nucleotide == 'U'
           return 'A'
        end 
    end   
    def self.of_dna(dna)
        dna.split('').map{ |nucl| self.get_dna_complement_of(nucl) }.join('')
    end

    def self.of_rna(rna)
        rna.split('').map{ |nucl| self.get_rna_complement_of(nucl) }.join('')
    end
end
