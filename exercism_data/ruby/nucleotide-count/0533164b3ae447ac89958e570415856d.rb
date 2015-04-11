class DNA

  def initialize(dna_string)
    @dna_string = dna_string
    @result = {"A"=>0, "T"=>0, "C"=>0, "G"=>0}
    @dna_string.chars.each{ |dna| @result[dna] += 1}
  end

  def nucleotide_counts
    @result
  end

  def count(nucleotide)
    if nucleotide == "U"
      0
    elsif ["A", "T", "C", "G"].include?(nucleotide)
      @result[nucleotide]
    else
      raise(ArgumentError)
    end
  end
end
