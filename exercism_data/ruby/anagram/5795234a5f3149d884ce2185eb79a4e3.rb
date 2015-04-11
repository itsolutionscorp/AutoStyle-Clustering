# encoding: utf-8

class Anagram
  def initialize(word)
    @word = word
    @upcase_chars = @word.chars.map { |char| char.upcase == char  }
  end

  def match(word_list)
    anagrams = AnagramFinder.new(@word, word_list).find
    upcase_words(anagrams)
  end

  private

  def upcase_words(words)
    words.map { |word| upcase_word(word) }
  end

  def upcase_word(word)
    [word.chars.to_a, @upcase_chars].transpose.map { |char, upcase| upcase ? char.upcase : char }.join
  end
end

class AnagramFinder
  def initialize(word, word_list)
    @word = word
    @word_list = word_list
  end

  def find
    @word_list.select { |word| have_same_chars?(word, @word) && !identical?(word, @word) }
  end

  private

  def have_same_chars?(word, other_word)
    word.downcase.chars.sort.join == other_word.downcase.chars.sort.join
  end

  def identical?(word, other_word)
    word.downcase == other_word.downcase
  end
end
