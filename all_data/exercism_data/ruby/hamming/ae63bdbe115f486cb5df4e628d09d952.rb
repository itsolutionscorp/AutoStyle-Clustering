class Hamming
  def self.compute(string1,string2)
    hamming_dist = 0
    string1.length.times do |n|
      hamming_dist += 1 if string1[n] != string2[n]
    end
    return hamming_dist
  end
end
