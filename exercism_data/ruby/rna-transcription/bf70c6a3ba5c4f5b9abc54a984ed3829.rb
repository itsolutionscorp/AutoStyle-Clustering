class Complement
    @@dna_to_rna = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
    }

    @@rna_to_dna = @@dna_to_rna.invert

    def self.of_dna(dna)
        rna = ''

        dna.each_char do |c|
            rna += @@dna_to_rna[c]
        end
        rna
    end

    def self.of_rna(rna)
        dna = ''

        rna.each_char do |c|
            dna += @@rna_to_dna[c]
        end
        dna
    end
end
