class Complement
    @@dna_to_rna = {
        'A' => 'U',
        'C' => 'G',
        'G' => 'C',
        'T' => 'A',
    }

    @@rna_to_dna = @@dna_to_rna.invert

    def self.of_dna(sequence)
        return sequence.chars.map { |s| @@dna_to_rna[s] }.join
    end

    def self.of_rna(sequence)
        return sequence.chars.map { |s| @@rna_to_dna[s] }.join
    end
end
