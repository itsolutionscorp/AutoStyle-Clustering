class Anagram
  def initialize(base_word)
    @base_word = base_word
    @base_components = reduce_to_letters(base_word)
  end

  def match(candidates)
    candidates.each_with_object([]) do |candidate,matches|
      next if same_word?(candidate)
      matches << candidate if reduce_to_letters(candidate) == @base_components
    end
  end

  private

  def same_word? (candidate)
    candidate.downcase == @base_word.downcase
  end

  def reduce_to_letters(word)
    word.downcase.chars.sort
  end
end
