class Hamming
  def self.compute(str1, str2)
    min_len = str1.length < str2.length ? str1.length : str2.length
    hamming_diff = 0
    (0..min_len-1).each do |i|
      hamming_diff += 1 if str1[i] != str2[i]
    end
    hamming_diff
  end
end
