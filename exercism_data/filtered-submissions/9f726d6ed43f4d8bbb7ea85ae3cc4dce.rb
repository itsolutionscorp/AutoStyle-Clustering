class Hamming

  def compute(strand_one, strand_two)
    strands = [strand_one, strand_two].sort_by!(&:length)
    dif = 0
    (0..strands.first.length-1).each do |c|
      return dif if strands.last.length < c
      dif += 1 if strands.first[c] != strands.last[c]
    end
    dif
  end
  
end
