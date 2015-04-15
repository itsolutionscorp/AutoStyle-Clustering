module Complement

    DNA_COMPLEMENTS = { 
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
    }
    
    RNA_COMPLEMENTS = { 
        'G' => 'C',
        'C' => 'G',
        'A' => 'T',
        'U' => 'A'
    }
    
    def self.of_dna( strand )
        of_strand( strand, DNA_COMPLEMENTS )
    end
    
    def self.of_rna( strand)
        of_strand( strand, RNA_COMPLEMENTS )
    end
    
    def self.of_strand( strand, complement )
        strand.chars.map do |nucleotide| 
            complement[ nucleotide ]
        end.join
    end
    
end
