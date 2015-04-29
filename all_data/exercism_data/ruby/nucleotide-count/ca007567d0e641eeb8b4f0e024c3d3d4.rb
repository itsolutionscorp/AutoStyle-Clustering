class Nucleotide
  VALID_NUCLEOTIDES = ["A", "T", "C", "G"]

  def self.from_dna(dna)
    new(dna)
  end

  def initialize(dna)
    validate!(dna)
    @dna = dna
  end

  def count(nucleotide)
    @dna.chars.count { |char| char == nucleotide }
  end

  def histogram
    {
      "A" => count("A"), "T" => count("T"),
      "C" => count("C"), "G" => count("G")
    }
  end

  private

  def validate!(dna)
    dna.chars.all? { |char| VALID_NUCLEOTIDES.include?(char) } or raise ArgumentError
  end
end
