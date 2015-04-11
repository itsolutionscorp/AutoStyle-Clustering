class Translation
  CODONS = {
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
    c = CODONS[codon]
    raise InvalidCodonError if c.nil?
    c
  end

  def self.of_rna(rna)
    rna.scan(/[A-Z]{3}/).each_with_object([]) do |r, res|
      c = CODONS[r]
      raise InvalidCodonError if c.nil?
      return res if c == "STOP"
      res << c
    end
  end
end

class InvalidCodonError < ArgumentError
end
