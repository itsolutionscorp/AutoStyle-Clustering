class Hamming
  def self.compute(strand_1 ,strand_2)
    length = [strand_1.length, strand_2.length].min
    diff_count = 0
    
    0.upto(length - 1) do |i|
      diff_count += 1 if strand_1[i] != strand_2[i]
    end
    
    diff_count
  end
end
