class Anagram
  def initialize(comparison_base_phrase)
    @comparison_base_phrase = comparison_base_phrase.downcase
  end

  def match(phrases)
    phrases.find_all do |phrase|
      are_anagrams(@comparison_base_phrase, phrase)
    end.reject do |phrase|
      @comparison_base_phrase == phrase.downcase
    end
  end

  private

  def are_anagrams(phrase_a, phrase_b)
    sort_phrase(phrase_a) == sort_phrase(phrase_b)
  end

  def sort_phrase(phrase)
    phrase.downcase.chars.sort
  end
end
