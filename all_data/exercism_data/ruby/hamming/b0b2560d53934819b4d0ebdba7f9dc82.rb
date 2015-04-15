class Hamming

  def self.compute_works(strand_one, strand_two)
    strands = [strand_one, strand_two].sort_by!(&:length)
    (0..strands.first.length-1).inject(0) do |dif, index|
      strands.first[index] != strands.last[index] ? dif + 1 : dif
    end
  end

  def self.compute(strand1, strand2) # mkwiatkowski
    strand1.chars.zip(strand2.chars).count do |base1, base2|
      base2 && base1 != base2
    end
  end
  
end
