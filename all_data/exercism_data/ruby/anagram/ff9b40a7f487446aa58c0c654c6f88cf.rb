class Anagram

  attr_reader :initial_word, :initial_word_letters

  def initialize initial_word
    @initial_word = initial_word.downcase
    @initial_word_letters = char_count @initial_word
  end

  def match wordset
    matches = Array.new

    wordset.each do |anagram_word|
      anagram_word_letters = char_count(anagram_word)
      matches << anagram_word unless hashes_differ? anagram_word_letters or same_word? anagram_word
    end

    return matches
  end

  def char_count any_word
      letters = Hash.new
      any_word = any_word.downcase

      any_word.each_char do |char|
          letters[char] = any_word.count char unless letters.include? char
      end

      return letters
  end

  def hashes_differ? anagram_word_hash
    anagram_word_hash != initial_word_letters
  end

  def same_word? anagram_word
    anagram_word.downcase == initial_word
  end
end
