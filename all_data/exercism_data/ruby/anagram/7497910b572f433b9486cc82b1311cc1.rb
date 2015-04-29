class Anagram

  def initialize(detector)
    @detector = detector.downcase
  end

  def match(possible_matches)
    possible_matches.uniq.map(&:downcase).inject([]) do |matches, test_word|
      matches << test_word if anagram?(test_word)
      matches
    end
  end

  private

  def anagram?(word)
    !exact_word_match?(word) && characters_match?(word)
  end

  def exact_word_match?(word)
    word == @detector 
  end

  def characters_match?(word)
    word.chars.sort == detector_char_sort
  end

  def detector_char_sort
    @detector_char_sort ||= @detector.chars.sort
  end

end
