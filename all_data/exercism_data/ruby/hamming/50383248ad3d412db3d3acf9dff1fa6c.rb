class Hamming

  def self.compute(str1, str2)
    count = 0
    max_length = [str1, str2].map(&:length).min
    max_length.times do |i|
      count += 1 if str1[i] != str2[i]
    end
    count
  end
end
