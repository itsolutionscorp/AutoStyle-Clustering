class Anagram
  attr_reader :anagram

  def initialize(anagram)
    @anagram = anagram
  end

  def match(words)

    processed_anagram = word_to_array(anagram)
    processed_words   = words_to_array(words)

    match = []

    processed_words.each do |word|
      if array_matcher(processed_anagram, word)
        match << array_to_word(word)
      end
    end

    match
  end

  private

  def words_to_array(words)
    processed_words = []

    words.each do |word|
      processed_word = word_to_array(word)
      processed_words << processed_word
    end

    processed_words
  end

  def word_to_array(word)
    word.split("")
  end

  def array_to_word(array)
    array.join
  end

  def array_matcher(array1, array2)
    if array1 - array2 == [] && array1.length == array2.length
      true
    else
      false
    end
  end
end
