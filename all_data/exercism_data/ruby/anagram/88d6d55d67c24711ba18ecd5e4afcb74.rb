class Anagram

  def initialize(word)
    @alphabet = chars_of(word)
  end

  def match(words)
    anagrams = []
    words.each do |w|
      anagrams << w if anagram?(w)
    end
    anagrams
  end

  private

  def anagram?(word)
    @alphabet == chars_of(word)
  end

  def chars_of(word)
    chars = Hash.new(0)
    word.downcase.chars.each do |c|
      chars[c] += 1
    end
    chars
  end

end
