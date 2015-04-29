class Complement
    def self.of_dna(nucleotide)
        dna_map = {
            'G' => 'C',
            'C' => 'G',
            'T' => 'A',
            'A' => 'U'
        }

        transcript = []
        nucleotide.split(//).each { |nucleo| transcript.push(dna_map[nucleo]) }
        transcript.join
    end

    def self.of_rna(nucleotide)
        rna_map = {
            'C' => 'G',
            'G' => 'C',
            'A' => 'T',
            'U' => 'A'
        }

        transcript = []
        nucleotide.split(//).each { |nucleo| transcript.push(rna_map[nucleo]) }
        transcript.join
    end
end
