class Hamming
  def self.compute(str1, str2)
    (0...[str1.length, str2.length].min).count do |i|
      str1[i] != str2[i]
    end
  end
end
