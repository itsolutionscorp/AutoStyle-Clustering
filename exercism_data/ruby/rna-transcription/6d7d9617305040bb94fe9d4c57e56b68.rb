module Complement
    DNA_TO_RNA = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
    }

    def Complement.of_dna dna
        dna.each_char.map { |nucleotide| DNA_TO_RNA[nucleotide] }.to_a.join
    end

    def Complement.of_rna rna
        rna.each_char.map { |nucleotide| DNA_TO_RNA.invert[nucleotide] }.to_a.join
    end
end
