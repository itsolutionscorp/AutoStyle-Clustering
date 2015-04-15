class Hamming

  def self.compute(str1, str2)
    diff = 0

    str1.split('').each_with_index do |char, i|
      diff += 1 if char != str2[i]
    end

    diff
  end

end
