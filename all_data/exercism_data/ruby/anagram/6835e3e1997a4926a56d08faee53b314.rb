class Anagram
  def initialize(key_word)
    @key_word = key_word
  end

  def match(words)
    filter_key_word = filter_key_word(@key_word, words)
    filter_by_letters(@key_word, filter_key_word)
  end

  private

  def filter_key_word(key_word, words)
    words.select { |word| word.downcase != key_word }
  end

  def filter_by_letters(key_word, words)
    key_word_sort = key_word.downcase.chars.sort
    words.select { |word| word.downcase.chars.sort == key_word_sort }
  end
end
