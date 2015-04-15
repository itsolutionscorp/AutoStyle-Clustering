class Complement

    DNA_COMPLEMENTS = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
    }

    RNA_COMPLEMENTS = {
        'A' => 'T',
        'U' => 'A',
        'C' => 'G',
        'G' => 'C'
    }

    def self.of_dna(dna)
        dna.each_char.map { |x| DNA_COMPLEMENTS[x] }.join ''
    end

    def self.of_rna(rna)
        rna.each_char.map { |x| RNA_COMPLEMENTS[x] }.join ''
    end
end
