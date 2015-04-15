class Anagram
  def initialize(word)
    @word = word
    @letters = letters_for(word)
  end

  attr_reader :word, :letters

  def match(candidates)
    candidates.select do |candidate|
      different_word?(candidate) && same_letters?(candidate)
    end
  end

  private

  def letters_for(candidate)
    candidate.downcase.chars.sort
  end

  def different_word?(candidate)
    word.downcase != candidate.downcase
  end
 
  def same_letters?(candidate)
    letters == letters_for(candidate)
  end
end
