class Hamming
  def compute(string1, string2)
    count = 0
    i = 0
    while  i < [string1.length, string2.length].min
      if string1[i] != string2[i]
        count += 1
      end
      i += 1
    end
    count
  end
end
