class Anagram
  def initialize(original)
    @original_word = original.downcase
    @original_chars = @original_word.chars.sort
  end

  def match(candidates)
    candidates.select do |candidate|
      next if candidate.downcase == @original_word
      @original_chars == candidate.downcase.chars.sort
    end.uniq(&:downcase)
  end
end
