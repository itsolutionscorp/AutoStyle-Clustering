require "pry"
class Anagram
  attr_reader :input

  def initialize(input)
    @input = input.downcase
  end

  def sorted_words_by_letters(data = [])
    data.map { |word| word.downcase.split("").sort }
  end

  def anagrams_and_the_index_position(sorted = [])
    sorted.each_with_index.select do |arr, index|
        index if arr == input.split("").sort
    end
  end

  def anagrams_index(index)
    index.flatten.select {|n| n.is_a? Fixnum }
  end

  def match(data = [])
    data.map do |word|
      if word == input
        return []
      end
    end
    sorted = sorted_words_by_letters(data)
    position = anagrams_and_the_index_position(sorted)
    anagrams_index = anagrams_index(position)
    anagrams_index.map {|pos| data[pos]}
  end

end
