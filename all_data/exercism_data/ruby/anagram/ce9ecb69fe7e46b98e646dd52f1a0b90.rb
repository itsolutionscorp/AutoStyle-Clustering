class Anagram
  attr_reader :anagram

  def initialize(subject)
    @anagram = subject
  end

  def match(potentials_array)
    length_matches = size_checker(potentials_array)
    length_matches.select do |word|
      word.split(//).all? { |letter| anagram.include?(letter) }
    end
  end

private
  def size_checker(potentials_array)
    potentials_array.select do |word|
      word.length == anagram.length
    end
  end
end
