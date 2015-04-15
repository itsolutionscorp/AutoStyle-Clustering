class Anagram
  def initialize(key_word)
    @key_word = key_word.downcase
  end

  def match(words_to_test)
    @key_counts ||= letter_counts(@key_word)
    words_to_test.each_with_object([]) do |tested_word,matches|
      if is_anagram_of_key_string?(tested_word.downcase) 
        matches.push(tested_word)
      end
    end
  end

  def letter_counts(input_string)
    input_string.chars.each_with_object(Hash.new(0)) do |c,hash|
      hash[c] += 1
    end
  end

  private
    def is_anagram_of_key_string?(tested_word)
        tested_counts = letter_counts(tested_word)
        tested_word != @key_word and tested_counts.eql?(@key_counts) 
    end
end
