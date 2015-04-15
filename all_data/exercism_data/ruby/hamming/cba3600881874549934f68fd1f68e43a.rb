class Hamming
  def self.compute(str1, str2)
    diff = 0
    [str1.length, str2.length].min.times do |i|
      diff += 1 unless str1[i] == str2[i]
    end
    diff
  end
end
