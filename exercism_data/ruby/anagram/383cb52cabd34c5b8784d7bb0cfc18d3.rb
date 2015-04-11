require "pry"
class Anagram
  attr_reader :input

  def initialize(input)
    @input = input.downcase
  end

  def remove_indentical(words)
    words.select do |word|
      word unless word.downcase == input.downcase
    end
  end

  def sorted_words_by_letters(words)
    words.map { |word| word.downcase.split("").sort }
  end

  def anagrams_and_the_index_position(sorted = [])
    sorted.each_with_index.select do |arr, index|
        index if arr == input.split("").sort
    end
  end

  def anagrams_index(index)
    index.flatten.select {|n| n.is_a? Fixnum }
  end

  def match(words)
    sorted = sorted_words_by_letters(words)
    position = anagrams_and_the_index_position(sorted)
    anagrams_index = anagrams_index(position)
    identical = anagrams_index.map {|pos| words[pos]}
    remove_indentical(identical)
  end

end
