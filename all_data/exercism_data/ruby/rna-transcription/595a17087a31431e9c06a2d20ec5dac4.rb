class Complement
    @dna = 'GCTA'
    @rna = 'CGAU'

    def self.of_dna(nucleotide)
        nucleotide.tr(@dna, @rna)
    end

    def self.of_rna(nucleotide)
        nucleotide.tr(@rna, @dna)
    end
end
