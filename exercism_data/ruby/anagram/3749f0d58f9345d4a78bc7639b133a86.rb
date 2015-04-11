class Anagram

  attr_reader :input

  def initialize(input)
    @input = input
  end

  def match(possible_matches)
    possible_matches.find_all do |match|
      match?(match)
    end
  end

  def match?(word)
    alphagram(input) == alphagram(word) && input.downcase != word.downcase
  end

  def alphagram(word)
    word.downcase.chars.sort.join
  end

end
