class Hamming
  def compute(string1, string2)
    distance = 0
    (0..string1.length-1).each do |i|
      if string1[i] != string2[i]
        distance += 1
      end
    end
    return distance
  end
end
