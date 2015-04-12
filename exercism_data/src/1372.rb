class Hamming
  def compute(strand_1, strand_2)
    if strand_1.eql?(strand_2)
     return 0
    else
      zipped_array = strand_1.split(//).zip(strand_2.split(//))
      hamming_distance = zipped_array.reject{|s1, s2| s1.eql?s2 }.count
    end
  end
end
