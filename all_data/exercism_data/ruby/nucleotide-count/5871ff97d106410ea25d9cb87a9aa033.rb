class DNA
  NUCLEOTIDES = "ACGTU"

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    if NUCLEOTIDES.include? nucleotide
      @sequence.count nucleotide
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    @sequence.chars.each_with_object({"A"=>0, "T"=>0, "C"=>0, "G"=>0}) { |n, r|
      r[n] += 1
    }
  end
end
