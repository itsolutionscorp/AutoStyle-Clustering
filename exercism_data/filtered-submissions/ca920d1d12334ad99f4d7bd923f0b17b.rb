class Hamming
  def compute(ntide_1, ntide_2)
    0.upto(ntide_1.length).count { |i| ntide_1[i] != ntide_2[i] }
  end
end
