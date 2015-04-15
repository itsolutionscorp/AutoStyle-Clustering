class Anagram
  def initialize word
    @word = word
  end

  def match possible_anagrams
    possible_anagrams.select {|possible| matches? possible }
  end

  def matches? possible_anagram
    letterbag(possible_anagram) == letterbag(@word)
  end

  private

  def letterbag word
    word.downcase.chars.sort
  end
end
