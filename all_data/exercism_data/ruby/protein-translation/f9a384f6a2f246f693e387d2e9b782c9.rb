class Translation
  PROTEINS = {
    "Methionine"    => %w(AUG),
    "Leucine"       => %w(UUA UUG),
    "Serine"        => %w(UCU UCC UCA UCG),
    "Cystine"       => %w(UGU UGC),
    "Tyrosine"      => %w(UAU UAC),
    "Tryptophan"    => %w(UGG),
    "Phenylalanine" => %w(UUU UUC),
    "STOP"          => %w(UAA UAG UGA),
  }

  CODONS = PROTEINS.each_with_object({}) do |(protein, codons), hash|
    Array(codons).each {|codon| hash[codon] = protein }
  end

  class << self
    def of_codon(str)
      CODONS.fetch(str){ raise InvalidCodonError }
    end

    def of_rna(str)
      str.scan(/.{3}/).each_with_object([]) do |triplet, result|
        of_codon(triplet).tap do |protein|
          return result if protein == "STOP"
          result << protein
        end
      end
    end
  end
end

class InvalidCodonError < ArgumentError
end
