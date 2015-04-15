class Anagram

  def initialize(word)
    @word = word
  end

  def match(anagrams)
    anagrams.keep_if {|anagram| is? anagram }
  end

  private

  def is?(anagram)
    chars_for(@word) == chars_for(anagram)
  end

  def chars_for(word)
    word.downcase.chars.sort
  end

end
