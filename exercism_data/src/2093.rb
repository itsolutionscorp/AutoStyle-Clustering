class Hamming
  def compute(string1, string2)
    count = 0
    i = 0
    while string1[i] && string2[i]
      count += 1 if string1[i] != string2[i]
      i += 1
    end
    count
  end
end
