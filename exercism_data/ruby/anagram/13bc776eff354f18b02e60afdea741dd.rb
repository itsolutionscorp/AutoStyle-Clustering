class Anagram
  def initialize(target)
    @target = target.downcase
    @canonical_target = canonical_form(@target)
  end

  def match(words)
    words.select do |word|
      next if word.downcase == @target
      canonical_form(word) == @canonical_target
    end
  end

  private

  def canonical_form(word)
    word.chars.sort
  end
end
