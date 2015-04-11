class Hamming
  def self.compute(word_1, word_2)
    differences = 0
    largest_common_index = Check.minimum(word_1.length, word_2.length)-1
    for i in 0..largest_common_index
      if Check.different(word_1[i], word_2[i])
        differences += 1
      end
    end
    differences
  end
end

class Check
  def self.minimum(a, b)
    if a < b
      a
    else
      b
    end
  end
  def self.different(a, b)
    a != b
  end
end
