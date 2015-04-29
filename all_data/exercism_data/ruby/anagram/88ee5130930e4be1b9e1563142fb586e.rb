class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.
      delete_if { |possible_match| possible_match.downcase == word }.
      select { |possible_match|
        possible_match.downcase.chars.sort == word.chars.sort
      }
  end

private
  attr_reader :word
end
