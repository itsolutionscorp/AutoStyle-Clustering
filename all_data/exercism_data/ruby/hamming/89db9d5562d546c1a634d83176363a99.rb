class Hamming

  def self.compute string1, string2
    distance = 0
    string1.length < string2.length ? length = string1.length : length = string2.length

    length.times do |i|
      if string1[i] != string2[i]
        distance += 1
      end
    end
    return distance
  end

end
