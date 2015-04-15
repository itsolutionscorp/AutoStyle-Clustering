class Hamming
  def self.compute(string1, string2)
    hamming_distance = 0
    string1.length.times do |index|
      hamming_distance += (string1[index] == string2[index] ? 0 : 1)
    end
    hamming_distance
  end
end
