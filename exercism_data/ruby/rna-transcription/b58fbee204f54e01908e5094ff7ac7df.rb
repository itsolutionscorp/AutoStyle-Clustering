class Complement
    DNA_NUCLEOTIDES = "ACGT"
    RNA_NUCLEOTIDES = "UGCA"

    def self.of_dna(seq)
        raise ArgumentError.new("Uracil (U) can't be found in DNA") if seq.match "U"
        seq.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
    end

    def self.of_rna(seq)
        raise ArgumentError.new("Thymine (T) can't be found in RNA") if seq.match "T"
        seq.tr(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
    end
end
