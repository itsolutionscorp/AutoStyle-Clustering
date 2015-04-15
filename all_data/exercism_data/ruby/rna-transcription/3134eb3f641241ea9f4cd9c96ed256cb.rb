class Complement
    COMPLEMENT = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
    }    

    def self.of_dna(dna)
        dna.split('').map{ |nucl| COMPLEMENT[nucl] }.join('')
    end

    def self.of_rna(rna)
        rna.split('').map{ |nucl| COMPLEMENT.key(nucl) }.join('')
    end
end
