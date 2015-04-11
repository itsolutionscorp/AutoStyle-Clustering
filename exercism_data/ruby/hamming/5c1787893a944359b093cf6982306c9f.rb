class Hamming
  def self.compute(str1, str2)
    differences = 0
    [str1.length, str2.length].min.times do |x|
      differences += 1 if str1[x] != str2[x]
    end
    differences
  end
end
