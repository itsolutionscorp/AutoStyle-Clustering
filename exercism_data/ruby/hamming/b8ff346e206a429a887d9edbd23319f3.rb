# "The Hamming distance between two strings of equal length is the
# number of positions at which the corresponding symbols are
# different." (Wikipedia)
class Hamming
  def self.compute(string_1, string_2)
    count = 0
    string_1.split(//).each_index do |i|
      break if i == string_2.length
      count += 1 unless string_1[i] == string_2[i]
    end
    count
  end
end
