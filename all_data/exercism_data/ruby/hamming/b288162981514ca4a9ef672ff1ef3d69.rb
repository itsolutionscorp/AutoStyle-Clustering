class Hamming
  def self.compute(string1, string2)
    distance = 0
    iter = string1.size > string2.size ? string2.size : string1.size
    0.upto(iter-1) do |index|
      distance += 1 if string1[index] != string2[index]
    end
    distance
  end
end
