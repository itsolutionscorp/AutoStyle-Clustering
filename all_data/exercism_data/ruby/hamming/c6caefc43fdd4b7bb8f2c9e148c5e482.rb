class Hamming

  def self.compute (str1, str2)
    length = [str1.length, str2.length].min
    (0...length).reject { |idx| str1[idx] == str2[idx] }.size
  end
end
