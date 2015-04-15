class Anagram

  def initialize(detector)
    @detector = detector.downcase.split("")
  end

  def match(words)

    filtered_words = words.select { |word| word.downcase != @detector.join }

    matched = filtered_words.select {|word| word.downcase.split("").sort == @detector.sort }

  end

end
