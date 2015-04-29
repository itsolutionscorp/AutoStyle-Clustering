class Anagram
  def initialize(word)
    @original_word = word
    @reference = @original_word.downcase
  end

  def match(list)
    list.each_with_object([]) do |w, l|
      l << w if anagram?(w)
    end
  end

  private
  def anagram?(candidate)
    candidate = candidate.downcase
    different(candidate) && same_length(candidate) && same_characters(candidate)
  end

  def different(candidate)
    @reference != candidate
  end

  def same_length(candidate)
    @reference.length == candidate.length
  end

  def same_characters(candidate)
    @reference.split('').sort == candidate.split('').sort
  end
end
