module Hamming
  def Hamming.compute(strand_1, strand_2)
    difference = 0
    
    [strand_1.length, strand_2.length].min.times do |index|
      difference += 1 if strand_1[index] != strand_2[index]
    end
    difference
  end
 
end
