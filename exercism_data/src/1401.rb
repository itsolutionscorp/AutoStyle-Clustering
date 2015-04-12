class Hamming

  # assumes that strings are the same length
  def compute(string1, string2)
    string1.length.times.count { |index| string1[index] != string2[index] }
  end

end