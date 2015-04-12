class Hamming

  def compute(strand1, strand2)
    count = 0
    strand1.chars.each_with_index do |x, index|
      count +=1 if strand2[index] && x != strand2[index]
    end
    count
  end
end
