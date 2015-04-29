class Anagram

  attr_reader :starting_word

  def initialize(starting_word)
    @starting_word = starting_word
  end

  def starting_letters
    starting_word.chars
  end

  def starting_letters_alphabetized
    starting_letters.sort
  end

  def match(comparison_words)
    matches = []
    comparison_words.each do |word|
      if equal_length?(word) && not_the_same_word?(word) && same_characters?(word)
        matches << word
      end
    end
    return matches
  end

  def equal_length?(word)
    starting_word.length == word.length
  end

  def not_the_same_word?(word)
    starting_word.downcase != word.downcase
  end

  def same_characters?(word)
    word = word.downcase.chars.sort.join
    original_word = starting_word.downcase.chars.sort.join
     word.casecmp(original_word) == 0
  end

end
