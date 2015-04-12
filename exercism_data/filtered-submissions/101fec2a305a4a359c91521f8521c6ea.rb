class Hamming
  def compute(strand1, strand2)
    count = 0
    strand1.chars.each_index {|i| count += 1  if strand1[i] != strand2[i]}   
    count
  end
end
