class Anagram
  attr_reader :anagram

  def initialize(word)
    @anagram = letters_from(word)
  end

  def match(words)
    words_collection = collection_from(words)
    find_anagram_in(words_collection)
  end

  private

  def collection_from(words)
    words.collect{ |word| letters_from(word) }
  end

  def find_anagram_in(words)
    match = []

    words.collect do |word|
      if matches?(anagram, word)
        match << word_processor(word)
      end
    end

    match
  end

  def letters_from(word)
    word.split("")
  end

  def word_processor(word_in_letters)
    word_in_letters.join
  end

  def matches?(word1, word2)
    word1.sort == word2.sort
  end
end
