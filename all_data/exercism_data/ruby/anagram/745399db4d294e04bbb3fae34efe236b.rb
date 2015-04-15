class Anagram
  def initialize(word)
    @word = word.downcase
    @archetype = archetype(@word)
  end

  def match(words)
    words.select {|word| anagram?(word.downcase) }
  end

  private
  def anagram?(word)
    !same?(word) && matches_archetype?(word)
  end

  def same?(sample)
    sample == @word
  end

  def matches_archetype?(word)
    archetype(word) == @archetype
  end

  def archetype(word)
    word.chars.sort.join
  end
end
