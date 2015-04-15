class Anagram
  attr_reader :collection

  def initialize(detector)
    @collection = detector.split('').sort
  end

  def match(possible_matches)
    possible_matches.each_with_object([]) do |word, matches|
      matches << detect_match(word)
    end.compact
  end

  def detect_match(word)
    word if collection == word.split('').sort
  end
end
