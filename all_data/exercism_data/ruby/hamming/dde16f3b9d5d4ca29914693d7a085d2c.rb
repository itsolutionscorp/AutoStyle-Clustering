class Hamming
  def self.compute(str1, str2)
    (0...str1.length).count do |i|
      str1[i] != str2[i]
    end
  end
end
