class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    words.each_with_object([]) do |word, anagrams|
      anagrams.push word if anagram?(word)
    end
  end

  private

  def anagram?(word)
    chars_for(word) == chars_for(@word)
  end

  def chars_for(word)
    word.downcase.chars.sort.join
  end

end
