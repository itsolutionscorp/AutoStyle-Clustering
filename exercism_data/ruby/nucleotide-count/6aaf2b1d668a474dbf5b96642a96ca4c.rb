class DNA

  attr_reader :nucleotides

  def initialize(strand)
    if valid_dna?(strand)
      @nucleotides = strand
    else
      raise ArgumentError 
    end
  end

  def valid_dna?(input)
    letters = input.split("")
    letters.all?{|letter| dna_nucleotides.include?(letter)}
  end

  def count(nucleotide)
    if valid_dna?(nucleotide)
      nucleotides.count(nucleotide)
    else
      raise ArgumentError.new("Bad DNA")
    end
  end

  def dna_nucleotides
    ['A', 'T', 'C', 'G']
  end

  def nucleotide_counts
    dna_nucleotides.each_with_object({}) do |nucleotide, result| 
      result[nucleotide] = count(nucleotide)
    end
  end

end
