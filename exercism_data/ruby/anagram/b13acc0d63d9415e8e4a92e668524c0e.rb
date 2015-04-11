class Anagram
  attr_reader :original_word

  def initialize(original_word)
    @original_word = original_word
  end


    def match(words_to_test)
      ensure_array(words_to_test).select{|word| anagram?(word, original_word)}
    end

private

def anagram?(word_a, word_b)
    return false if word_a.downcase == word_b.downcase
    word_to_sorted_array(word_a) == word_to_sorted_array(word_b)
end

def word_to_sorted_array(word)
  word.downcase.chars.sort
end

def ensure_array(array_or_string)
  if array_or_string.class == String
    array_or_string.split(",") 
  else
    array_or_string
  end
end

end
