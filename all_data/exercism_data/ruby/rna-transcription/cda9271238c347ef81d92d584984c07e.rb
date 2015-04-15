
class Complement

    LOOKUP_DNA_COMPLEMENT = {'G' => 'C',
                             'C' => 'G',
                             'T' => 'A',
                             'A' => 'U',
                            }
                            
    LOOKUP_RNA_COMPLEMENT = {'C' => 'G',
                             'G' => 'C',
                             'A' => 'T',
                             'U' => 'A',
                            } 
                            
    def self.of_dna(sequence)
        rna = ''
        sequence.each_char do |nucleotide|
            rna += LOOKUP_DNA_COMPLEMENT[nucleotide]
        end
        return rna
    end
    
    def self.of_rna(sequence)
        dna = ''
        sequence.each_char do |nucleotide|
            dna += LOOKUP_RNA_COMPLEMENT[nucleotide]
        end
        return dna
    end
    
end
