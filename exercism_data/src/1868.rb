class Hamming

  def compute(strand1, strand2)
    diffs = 0
    shorter = strand1 < strand2 ? strand1 : strand2
    (0...shorter.length).each do |i|
      diffs +=1 if strand1[i] != strand2[i] 
    end
    diffs
  end

end

puts Hamming::compute('A', 'BCD')
