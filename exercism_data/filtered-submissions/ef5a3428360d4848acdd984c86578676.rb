class Hamming
  def compute(string1, string2)
    h = 0
    0.upto(string1.size - 1) do |index|
      if string1[index] != string2[index]
        h +=1
      end
    end
    h
  end
end
