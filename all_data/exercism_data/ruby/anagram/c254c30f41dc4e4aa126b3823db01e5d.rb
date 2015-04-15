class Anagram
  def initialize(word)
    @original_word = word.downcase
    @sorted_word_letters = sort_letters(word)
  end

  def match(test_words)
    test_words.select { |word| is_anagram?(word) }
  end

  private

  def is_anagram?(word)
    current_word = word.downcase
    @original_word != word.downcase &&
      @sorted_word_letters == sort_letters(word)
  end

  def sort_letters(word)
    word.downcase.split('').sort.join('')
  end
end
