class Hamming

  def self.compute(original, mutated)
    result = 0
    shorter_length(original, mutated).times do |i|
      if original[i] != mutated[i]
        result +=1
      end
    end
    result
  end

  private

  def self.shorter_length(s1, s2)
    if (s1.length < s2.length)
      s1.length
    else
        s2.length
    end
  end

end
