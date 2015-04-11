class DNA

  def initialize(strand)
    unless strand.split("").include?("U") || strand.split("").include?("J")
      @strand = strand
    else
      raise ArgumentError.new("Sorry, we're only looking for DNA, not RNA.")
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
    counts = {}
    ["A","T","C","G"].each do |nucleotide|
      counts[nucleotide] = count(nucleotide)
    end
    counts
  end

  def valid_nucleotides
    ["A","T","C","G","U"]
  end

  def strand
    @strand.split("")
  end

end
