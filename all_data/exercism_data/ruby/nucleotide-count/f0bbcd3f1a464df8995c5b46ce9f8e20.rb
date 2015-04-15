class DNA

  def initialize nucleotides
    raise ArgumentError if nucleotides.match(/[^AGTC]/)
    @nucleotides = nucleotides
  end

  def count nucleotide
    raise ArgumentError unless "AGTCU".include? nucleotide
    result = 0
    @nucleotides.each_char do |c|
      result += 1 if (c == nucleotide)
    end
    result
  end

  def nucleotide_counts
    result = {'A'=> 0, 'G'=> 0, 'C'=> 0, 'T'=> 0}
    @nucleotides.each_char do |c|
      result[c] += 1
    end
    result
  end

end
