class Anagram
  def initialize(word)
    @word = word.downcase
    @archetype = archetype(@word)
  end

  def match(candidates)
    candidates.find_all {|candidate| anagram?(candidate) }
  end

  private
  def anagram?(sample)
    !same?(sample) &&
      matches_archetype?(sample)
  end

  def same?(sample)
    sample.downcase == @word
  end

  def matches_archetype?(word)
    archetype(word) == @archetype
  end

  def archetype(word)
    word.downcase.chars.sort.join
  end
end
