class Anagram
  attr_reader :letters

  def initialize(word)
    @letters = word_to_letters(word)
  end

  def match(words)
    words_array = words_to_array(words)
    anagram_finder(words_array)
  end

  private

  def words_to_array(words)
    words_array = []

    words.each do |word|
      words_array << word_to_letters(word)
    end

    words_array
  end

  def word_to_letters(word)
    word.split("")
  end

  def array_to_word(array)
    array.join
  end

  def array_matcher(array1, array2)
    array1.sort == array2.sort
  end

  def anagram_finder(words_array)
    match = []

    words_array.each do |word|
      if array_matcher(letters, word)
        match << array_to_word(word)
      end
    end

    match
  end
end
