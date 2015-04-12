class Hamming
  def compute(strand1, strand2)
    val = 0
    strand1.chars.each_with_index {|c, i|
      val+=1 unless strand2[i] == c
    }
    val
  end
end
