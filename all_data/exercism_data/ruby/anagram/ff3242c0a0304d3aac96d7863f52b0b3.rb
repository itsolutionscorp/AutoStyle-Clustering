class Anagram
  def initialize word
    @word = word
  end

  def match possible_anagrams
    possible_anagrams.select {|word| is_anagram? word }
  end

  def is_anagram? word
    letterbag(word) == letterbag(@word)
  end

  private

  def letterbag word
    word.downcase.chars.sort
  end
end
