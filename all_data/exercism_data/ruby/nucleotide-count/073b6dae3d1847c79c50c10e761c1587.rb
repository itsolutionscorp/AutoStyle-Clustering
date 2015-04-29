class DNA

  def initialize(strand)
    check_strand(strand)
  end

  def check_strand(strand)
    if strand.split("").include?("U")
      raise ArgumentError.new("Sorry, we're only looking for DNA, not RNA.")
    elsif strand.split("").include?("J")
      raise ArgumentError.new("Sorry, that is not a valid nucleotide.")
    else
      @strand = strand
    end
  end

  def count(nucleotide)
    if valid_nucleotides.include?(nucleotide)
      matches(nucleotide).count
    else
      raise ArgumentError.new("Sorry, #{nucleotide} is not a valid nucleotide.")
    end
  end

  def matches(nucleotide)
    strand.select do |letter|
      letter == nucleotide
    end
  end

  def nucleotide_counts
    valid_dna.each_with_object(Hash.new) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  def valid_nucleotides
    ["A","T","C","G","U"]
  end

  def valid_dna
     ["A","T","C","G"]
  end

  # def strand
  #   @strand.split("")
  # end

end
