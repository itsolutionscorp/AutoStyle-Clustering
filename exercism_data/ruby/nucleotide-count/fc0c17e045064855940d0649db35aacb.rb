class DNA

  attr_reader :sequence

  def initialize(nucleotides)
    @sequence = nucleotides.chars
    raise ArgumentError unless sequence.all? {|elem| valid? elem}
  end

  def count(nucleotide)
    if valid?(nucleotide)
      sequence.count(nucleotide)
    else
      raise ArgumentError
    end
  end

  def valid?(nucleotide)
    valid_nucleotides.include? nucleotide
  end

  def nucleotide_counts
    {
      "A" => count("A"),
      "T" => count("T"),
      "G" => count("G"),
      "C" => count("C")
    }
  end

  def valid_nucleotides
    ['A', 'C', 'G', 'T']
  end

end
