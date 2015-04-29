class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select{ |w| anagrams?(w,@word) }
  end

  private

  def sort_chars(word)
    word.downcase.chars.sort
  end

  def anagrams?(w1,w2)
    sort_chars(w1) == sort_chars(w2)
  end
end
