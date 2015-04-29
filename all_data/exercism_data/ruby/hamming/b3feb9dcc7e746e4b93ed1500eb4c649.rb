class Hamming
  def self.compute (dna, dna_copy)
    comparator = Comparator.new
    length = comparator.get_minimum_length(dna, dna_copy)
    comparator.count_differences(dna, dna_copy, length)
  end
end

class Comparator
  def get_minimum_length(first_word, second_word)
    [first_word.length, second_word.length].min
  end

  def count_differences(first_word, second_word, length_to_check)
    value = 0
    for i in 1..length_to_check
      if(first_word[i-1]!=second_word[i-1])
        value += 1
      end
    end
    value
  end
end
