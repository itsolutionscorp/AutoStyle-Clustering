class Hamming

  def compute(strand1, strand2)
    if strand1.length > strand2.length
    nucleotide = strand2.chars.zip(strand1.chars)
    else
    nucleotide = strand1.chars.zip(strand2.chars)
    end
      difference = 0
      nucleotide.each_with_index do |n|
      difference += 1 if n[0] != n[1]
    end
    difference
  end

end
