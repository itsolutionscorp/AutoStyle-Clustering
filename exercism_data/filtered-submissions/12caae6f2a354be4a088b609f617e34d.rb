class Hamming
  def compute(string1, string2)
    distance = 0
    0.upto(string1.length-1) do |index|
      break if index == string1.length || index == string2.length
      distance += 1 if string1[index] != string2[index]
    end
    distance
  end
end
