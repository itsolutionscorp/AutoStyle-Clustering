class DNA
  def initialize(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    if invalid_nucleotide(nucleotide)
      raise ArgumentError
    end
    @dna_string.split("").select {|n| n == nucleotide}.length
  end

  def nucleotide_counts
    result = {}
    result["A"] = @dna_string.count("A")
    result["T"] = @dna_string.count("T")
    result["C"] = @dna_string.count("C")
    result["G"] = @dna_string.count("G")
    result
  end

  def invalid_nucleotide(nucleotide)
    if ["A", "T", "C", "G", "U"].include?(nucleotide)
      false
    else
      true
    end
  end
end
