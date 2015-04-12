class Hamming

  def compute(string1, string2)
    stringlength = string1.size
    (0...stringlength).count { |x| string1[x] != string2[x] }
  end

end
