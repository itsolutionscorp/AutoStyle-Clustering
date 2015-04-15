class DNA
  Nucleotides = {"A" => 0, "T" => 0, "C" => 0, "G" => 0}

  def initialize(dna_string)
    @dna_string = dna_string
    @result = Nucleotides.dup
    @dna_string.chars.each{ |dna| @result[dna] += 1}
  end

  def nucleotide_counts
    @result
  end

  def count(nucleotide)
    if nucleotide == "U"
      0
    elsif Nucleotides.keys.include?(nucleotide)
      @result[nucleotide]
    else
      raise(ArgumentError, "Argument Error")
    end
  end
end
