class Anagram
  def initialize(word)
    @word = word || ""
    @letters = letters_in_order(@word)
  end

  def match(candidates)
    candidates.find_all do |candidate|
      ! same_word?(candidate) && anagram_of?(candidate)
    end
  end

private

  def letters_in_order(word)
    word.downcase.chars.sort
  end

  def same_word?(word)
    @word.casecmp(word) == 0
  end

  def anagram_of?(word)
    @letters == letters_in_order(word)
  end

end
