class DNA
  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def count(nucleotide)
    if !valid_nucleotide(nucleotide)
      raise ArgumentError
    end
    @nucleotides.chars.select {|n| n == nucleotide}.length
  end

  def nucleotide_counts
    { "A" => @nucleotides.count("A"),
      "T" => @nucleotides.count("T"),
      "C" => @nucleotides.count("C"),
      "G" => @nucleotides.count("G") }
  end

  def valid_nucleotide(nucleotide)
    ["A", "T", "C", "G", "U"].include?(nucleotide)
  end
end
