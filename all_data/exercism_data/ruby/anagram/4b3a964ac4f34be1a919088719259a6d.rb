class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.
      reject do |word| #For the record, I disagree that words are not their own anagrams.
        word.downcase == @word.downcase
      end.
      select do |word|
        canonicalize(word) == canonicalize(@word)
      end
  end

  private
  def canonicalize(word)
    word.downcase.split('').sort.join
  end
end
