class Hamming
  def compute(strand1, strand2)
    strand1.each_char.zip(strand2.each_char).
      select{|t| t[0] != t[1]}.count
  end
end
