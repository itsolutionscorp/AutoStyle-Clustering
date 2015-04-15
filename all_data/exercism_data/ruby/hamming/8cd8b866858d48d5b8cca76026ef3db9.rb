class Hamming
  def self.compute(string1, string2)
    if string2.size < string1.size
      string1, string2 = string2, string1
    end

    string1.size.times.count do |i|
      string1[i] != string2[i]
    end
  end
end
