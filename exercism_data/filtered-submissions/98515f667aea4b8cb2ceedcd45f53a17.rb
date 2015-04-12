class Hamming

  def compute(strand_one, strand_two)
    strands = [strand_one, strand_two].sort_by!(&:length)
    (0..strands.first.length-1).inject(0) do |dif, index|
      strands.first[index] != strands.last[index] ? dif + 1 : dif
    end
  end
  
end
