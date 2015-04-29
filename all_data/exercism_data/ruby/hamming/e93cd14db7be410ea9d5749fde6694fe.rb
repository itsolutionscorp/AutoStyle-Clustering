class Hamming
  def self.compute (dna, dna_copy)
    length = get_minimum_length(dna, dna_copy)
    count_differences(dna, dna_copy, length)
  end

  private
  def self.get_minimum_length(first_word, second_word)
    [first_word.length, second_word.length].min
  end

  def self.count_differences(first_word, second_word, length_to_check)
    value = 0
    for i in 1..length_to_check
      if(first_word[i-1]!=second_word[i-1])
        value += 1
      end
    end
    value
  end
end
