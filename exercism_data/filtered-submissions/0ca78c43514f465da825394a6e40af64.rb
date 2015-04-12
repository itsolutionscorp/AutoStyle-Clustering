class Hamming

  def compute(strand_one, strand_two)
    comparison = []
    strand_one = strand_one[0..(strand_two.length - 1)] if strand_one.length > strand_two.length
    strand_one_bases, strand_two_bases = strand_one.chars, strand_two.chars
    
    for x in 0..(strand_one.length - 1)
      comparison << (strand_one_bases[x] == strand_two_bases[x])
    end

    comparison.count(false)
  end

end
