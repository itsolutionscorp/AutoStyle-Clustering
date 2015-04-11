class Anagram
  def initialize(word)
    @original_word = word
    @sorted_word_letters = sort_letters(word)
  end

  def match(test_words)
    test_words.select { |word| is_anagram?(word) }
  end

  private

  def is_anagram?(word)
    @original_word.downcase != word.downcase &&
        @sorted_word_letters == sort_letters(word)
  end

  def sort_letters(word)
    word.downcase.split('').sort.join('')
  end
end
