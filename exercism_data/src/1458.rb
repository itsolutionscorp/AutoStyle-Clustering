class Hamming

  def compute(strand1, strand2)
    nucleotide = strand2.chars.zip(strand1.chars)
    unless strand1.length > strand2.length
      nucleotide = strand1.chars.zip(strand2.chars)
    end
    difference = 0
    nucleotide.count do |n|
    difference += 1 if n[0] != n[1]
    end
  end

end
