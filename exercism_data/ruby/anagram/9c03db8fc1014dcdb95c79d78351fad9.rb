class Anagram

  def initialize(detector)
    @detector = detector.downcase
  end

  def match(possible_matches)
    possible_matches.uniq.map(&:downcase).inject([]) do |result, test_word|
      result << test_word if anagram(test_word)
      result
    end
  end

  def anagram(word)
    !exact_match(word) && characters_match(word)
  end

  private

  def exact_match(word)
    word == @detector 
  end

  def characters_match(word)
    word.chars.sort == detector_char_sort
  end

  def detector_char_sort
    @detector_char_sort ||= @detector.chars.sort
  end

end
