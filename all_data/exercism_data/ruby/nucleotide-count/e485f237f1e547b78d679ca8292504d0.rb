class DNA
  def initialize(dna_string)
    @dna_string = dna_string
    @valid_nucleotides = ["A", "T", "C", "G", "U"]
  end

  def count(nucleotide)
    if !@valid_nucleotides.include?(nucleotide)
      raise ArgumentError
    else
      @dna_string.split("").select {|n| n == nucleotide}.length
    end
  end

  def nucleotide_counts
    ["A", "T", "C", "G"].each_with_object({}) do |nucleotide, result|
      result[nucleotide] = count(nucleotide)
    end
  end
end
