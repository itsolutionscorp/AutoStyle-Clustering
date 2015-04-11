class Hamming

  def initialize
  end

  def self.compute(str1, str2)
    count = 0
    if str1.length > str2.length
      count = self.algo(str1,str2)
    else
      count = self.algo(str2,str1)
    end

    return count
  end

  def self.algo(str1, str2)
    count = 0

    idx = 0
    str1.each_char do |char|
      if str1[idx] != str2[idx]
        count += 1
      end
      idx += 1
    end

    return count
  end

end
