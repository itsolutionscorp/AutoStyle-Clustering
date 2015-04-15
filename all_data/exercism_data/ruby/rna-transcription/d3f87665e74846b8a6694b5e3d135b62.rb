module Complement
    DNA_TO_RNA = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
    }

    RNA_TO_DNA = DNA_TO_RNA.invert

    def Complement.of_dna dna
        dna.each_char.map { |nucleotide| DNA_TO_RNA[nucleotide] }.join
    end

    def Complement.of_rna rna
        rna.each_char.map { |nucleotide| RNA_TO_DNA[nucleotide] }.join
    end
end
