class InvalidCodonError < ArgumentError
end

class Translation

  CODON_PROTIENS = {
    AUG: "Methionine",
    UUU: "Phenylalanine",
    UUC: "Phenylalanine",
    UUA: "Leucine",
    UUG: "Leucine",
    UCU: "Serine",
    UCC: "Serine",
    UCA: "Serine",
    UCG: "Serine",
    UAU: "Tyrosine",
    UAC: "Tyrosine",
    UGU: "Cystine",
    UGC: "Cystine",
    UGG: "Tryptophan",
    UAA: "STOP",
    UAG: "STOP",
    UGA: "STOP",
  }

  class << self
    def of_codon(codon)
      protein = CODON_PROTIENS[codon.to_sym]
      raise InvalidCodonError unless protein
      protein
    end

    def of_rna(strand)
      proteins = []
      strand.chars.each_slice(3) do |codon|
        protein = of_codon(codon.join)
        break if protein == "STOP"
        proteins << protein
      end
      proteins
    end
  end
end
