# encoding: utf-8

class Anagram
  def initialize(word)
    @word = word
    @upcase_chars = Word.new(@word).upcase_chars
  end

  def match(word_list)
    word_list = prepare_word_list(word_list)
    anagrams = AnagramFinder.new(@word, word_list).find
    upcase_words(anagrams)
  end

  private

  def prepare_word_list(word_list)
    word_list = WordList.new(word_list)
    word_list.select_words_with_same_length!(@word.length)
    word_list.select_words_with_same_chars!(@word)
    word_list.downcase!
    word_list.remove!(@word)
    word_list.words
  end

  def upcase_words(words)
    words.map { |word| upcase_word(word) }
  end

  def upcase_word(word)
    [word.chars.to_a, @upcase_chars].transpose.map { |char, upcase| upcase ? char.upcase : char }.join
  end
end

class WordList
  attr_reader :words

  def initialize(words)
    @words = words
  end

  def select_words_with_same_length!(length)
    @words.select! { |word| word.length == length }
  end

  def select_words_with_same_chars!(word)
    prepared_word = word.downcase.chars.sort
    prepared_words = @words.map { |word| word.downcase.chars.sort }
    prepared_words.select! { |word| word == prepared_word }
  end

  def downcase!
    @words.map!(&:downcase)
  end

  def remove!(other_word)
    @words.reject! { |word| word == other_word }
  end
end

class AnagramFinder
  def initialize(word, word_list)
    @word = word
    @word_list = word_list
  end

  def find
    find_anagrams(permutate_words(@word_list))
  end

  private

  def permutate_words(word_list)
    @word_list.reduce({}) do |hash, word|
      permutations = permutate_word(word)
      hash.merge!(word => permutations)
    end
  end

  def permutate_word(word)
    word.chars.to_a.permutation.map(&:join)
  end

  def find_anagrams(hash)
    hash.select { |word, perms| perms.include?(@word.downcase) }.keys
  end
end

class Word
  def initialize(word)
    @word = word
  end

  def upcase_chars
    @word.chars.map { |char| char.upcase == char  }
  end
end
