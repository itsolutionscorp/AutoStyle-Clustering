class Hamming

  def self.compute(str1, str2)
    count = 0
    length = [str1.length, str2.length].min
    length.times do |i|
      count += 1 if str1.chars[i] != str2.chars[i]
    end
    count
  end
end