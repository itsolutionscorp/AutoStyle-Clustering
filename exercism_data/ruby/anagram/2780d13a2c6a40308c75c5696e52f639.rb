class Anagram
  attr_reader :original_word
  private :original_word

  def initialize(original_word)
    @original_word = original_word
  end

  def match(potential_anagrams)
    potential_anagrams.select do |potential_anagram|
      anagram?(potential_anagram.downcase)
    end
  end

  private

  def anagram?(potential_anagram)
    use_same_letters?(original_word, potential_anagram) &&
      different_words?(original_word, potential_anagram)
  end

  def use_same_letters?(a, b)
    sorted_chars(a) == sorted_chars(b)
  end

  def different_words?(a, b)
    a != b
  end

  def sorted_chars(str)
    str.downcase.chars.sort
  end
end
