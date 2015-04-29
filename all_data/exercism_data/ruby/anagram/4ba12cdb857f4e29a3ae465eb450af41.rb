class Anagram

  def initialize(detector)
    @detector = detector
  end

  def match(words)
    words.select { |word| word.downcase != @detector && word.downcase.split("").sort == @detector.downcase.split("").sort}
  end

end
