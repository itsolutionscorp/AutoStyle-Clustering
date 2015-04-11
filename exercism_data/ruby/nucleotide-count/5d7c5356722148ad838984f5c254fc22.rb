class DNA
  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def count(nucleotide)
    if !valid_nucleotide(nucleotide)
      raise ArgumentError
    end
    @nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    { "A" => count("A"),
      "T" => count("T"),
      "C" => count("C"),
      "G" => count("G") }
  end

  def valid_nucleotide(nucleotide)
    ["A", "T", "C", "G", "U"].include?(nucleotide)
  end
end
