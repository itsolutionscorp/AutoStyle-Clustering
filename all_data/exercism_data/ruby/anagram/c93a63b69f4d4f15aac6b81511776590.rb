class Anagram
  def initialize(key_word)
    @key_word = key_word.downcase
  end

  def match(words_to_test)
    @key_counts ||= letter_counts(@key_word)
    words_to_test.select { |word| is_anagram_of_key_string?(word.downcase) } 
  end

  private
    def is_anagram_of_key_string?(tested_word)
        tested_counts = letter_counts(tested_word)
        tested_word != @key_word and tested_counts.eql?(@key_counts) 
    end

    def letter_counts(input_string)
      input_string.chars.each_with_object(Hash.new(0)) do |c,hash|
        hash[c] += 1
      end
    end
end
