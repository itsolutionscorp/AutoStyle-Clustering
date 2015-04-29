class Anagram

  attr_reader :starting_word

  def initialize(starting_word)
    @starting_word = starting_word
  end

  def match(candidates)
    matches = []
    candidates.each do |word|
      if anagram?(word)
        matches << word
      end
    end
    matches
  end

  def anagram?(word)
    same_word?(word) && same_characters?(word)
  end

  def same_word?(word)
    starting_word.downcase != word.downcase
  end

  def same_characters?(word)
    word = word.downcase.chars.sort.join
    original_word = starting_word.downcase.chars.sort.join
    word == original_word
  end

end
