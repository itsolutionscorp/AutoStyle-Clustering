class Anagram
  def initialize(word)
    @word = word
    @sortedWord = word.downcase.chars.sort.join
  end

  def match(words)
    words.select {|w| has_anagram(w)}
  end

  def has_anagram(otherWord)
    return false if otherWord.downcase == @word.downcase
    @sortedWord == otherWord.downcase.chars.sort.join
  end
end
