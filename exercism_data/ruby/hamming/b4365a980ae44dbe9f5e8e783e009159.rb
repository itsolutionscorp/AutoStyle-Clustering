class Hamming
  def self.compute(str1, str2)
    count = 0
    (0...[str1.length, str2.length].min).each do |char|
      count += 1 if str1[char] != str2[char]
    end
    count
  end
end
