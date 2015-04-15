class Anagram
  def initialize(word)
    @word = word.downcase
    @required_letters = letters(word)
  end

  def match(possible_anagrams)
    possible_anagrams.reject { |candidate| exact_match?(candidate) }.
      find_all { |candidate| anagram?(candidate) }
  end

  private

  attr_reader :required_letters, :word

  def letters(string)
    string.downcase.chars.sort
  end

  def exact_match?(candidate)
    candidate.downcase == word
  end

  def anagram?(candidate)
    letters(candidate) == required_letters
  end
end
