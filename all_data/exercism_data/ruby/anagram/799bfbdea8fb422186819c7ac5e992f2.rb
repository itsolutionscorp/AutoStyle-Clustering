class Anagram
  def initialize(target)
    @target = target.downcase
    @canonical_target = canonical_anagram(@target)
  end

  def match(words)
    words.select do |word|
      next if word.downcase == @target
      canonical_anagram(word) == @canonical_target
    end
  end

  private

  def canonical_anagram(word)
    word.chars.sort
  end
end
