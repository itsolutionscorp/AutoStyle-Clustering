class Complement
    DNA_TO_RNA = {
        'C' => 'G', 
        'G' => 'C',
        'T' => 'A',
        'A' => 'U'
    }

    def self.of_dna(dna)
        dna.chars.map { |nucleotide| DNA_TO_RNA[nucleotide] }.join
    end

    def self.of_rna(rna)
        rna.chars.map { |nucleotide| DNA_TO_RNA.key(nucleotide) }.join
    end

end
