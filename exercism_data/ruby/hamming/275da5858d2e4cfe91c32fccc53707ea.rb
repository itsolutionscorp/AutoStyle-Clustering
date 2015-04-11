class Hamming
  def self.compute (dna, dna_copy)
    comparator = DNA_Comparator.new
    comparator.count_differences(dna, dna_copy)
  end
end

class DNA_Comparator
  def count_differences(first_word, second_word)
      length = get_minimum_length(first_word, second_word)
      first_array = array_of_n_first_characters(first_word, length)
      second_array = array_of_n_first_characters(second_word, length)
      count_different_elements(first_array, second_array)
  end

  def get_minimum_length(first_word, second_word)
    [first_word.length, second_word.length].min
  end

  def array_of_n_first_characters(word, num_of_characters)
    word.split('').take(num_of_characters)
  end

  def count_different_elements(first_table, second_table)
    first_table.zip(second_table).count{|zipped_element| zipped_element[0]!=zipped_element[1]}
  end
end
