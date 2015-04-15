class Hamming

  def Hamming.compute(strand_one, strand_two)
    dif = 0
    self.getRange(strand_one, strand_two).each do |c|
      return dif if strand_two.length < c
      dif = dif + 1 if strand_one[c] != strand_two[c]
    end
    dif
  end

  def Hamming.getRange(strand_one, strand_two)
    one_len, two_len = strand_one.length, strand_two.length
    max_length = one_len >= two_len ? one_len : two_len
    cursor_bound = max_length - 1
    (0..cursor_bound)
  end
  
end
