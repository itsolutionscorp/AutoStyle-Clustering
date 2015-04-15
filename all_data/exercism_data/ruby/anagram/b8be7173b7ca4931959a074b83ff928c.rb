class Anagram
  def initialize(comparison_base_phrase)
    @comparison_base_phrase = comparison_base_phrase.downcase
    @comparison_base = sort_phrase(@comparison_base_phrase)
  end

  def match(phrases)
    phrases.reject do |phrase|
      @comparison_base_phrase == phrase.downcase
    end.select do |phrase|
      @comparison_base == sort_phrase(phrase)
    end
  end

  private

  def sort_phrase(phrase)
    phrase.downcase.chars.sort.join
  end
end
