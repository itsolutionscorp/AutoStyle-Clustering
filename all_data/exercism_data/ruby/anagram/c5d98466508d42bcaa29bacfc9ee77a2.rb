class Anagram
  def initialize(word)
    @word = word
    @letters = ordered_letters(@word)
  end

  def match(candidates)
    candidates.find_all do |candidate|
      @word.casecmp(candidate) != 0 && ordered_letters(candidate) == @letters
    end
  end

private

  def ordered_letters(string)
    string.downcase.chars.sort
  end

end
