class InvalidCodonError < StandardError; end

class String
  def each_slice(n, &block)
    self.scan(/.{1,#{n}}/).each { |chunk| yield chunk if block_given? }
  end
end

module Translation
  CODONS_TO_PROTEINS = {
    "AUG" => "Methionine",
    "UUU" => "Phenylalanine",
    "UUC" => "Phenylalanine",
    "UUA" => "Leucine",
    "UUG" => "Leucine",
    "UCU" => "Serine",
    "UCC" => "Serine",
    "UCA" => "Serine",
    "UCG" => "Serine",
    "UAU" => "Tyrosine",
    "UAC" => "Tyrosine",
    "UGU" => "Cystine",
    "UGC" => "Cystine",
    "UGG" => "Tryptophan",
    "UAA" => "STOP",
    "UAG" => "STOP",
    "UGA" => "STOP"
  }

  def self.of_codon(codon)
    CODONS_TO_PROTEINS[codon] or raise InvalidCodonError.new('Invalid Codon')
  end

  def self.of_rna(rna)
    rna.each_slice(3).inject([]) do |proteins, codon|
      protein = of_codon(codon)
      if protein == "STOP"
        break proteins
      else
        proteins << protein
      end
    end
  end
end
