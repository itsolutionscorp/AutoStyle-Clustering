class DNA
  def initialize(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    throw ArgumentError if !valid_nucleotide?(nucleotide)

    @dna_string.split("").select do |n|
      n == nucleotide
    end.length
  end

  def valid_nucleotide?(nucleotide)
    ["A", "T", "C", "G", "U"].include?(nucleotide)
  end

  def nucleotide_counts
    ["A", "T", "C", "G"].each_with_object({}) do |nucleotide, result|
      result[nucleotide] = count(nucleotide)
    end
  end
end
