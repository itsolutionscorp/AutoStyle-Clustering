class InvalidCodonError < StandardError
end

module Translation
  PAIRS = {
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

  def self.of_codon codon
    PAIRS[codon]
  end

  def self.of_rna strand
    raise InvalidCodonError if strand.size < 9

    translation = []

    strand[0...9].scan(/.{1,3}/).each do |codon|
      polypeptide = self.of_codon codon
      break if polypeptide === "STOP"
      translation << polypeptide
    end

    translation
  end
end
