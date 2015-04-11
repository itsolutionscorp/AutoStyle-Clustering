class Anagram

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(words)
    words.select do |candidate|
      AnagramAnalyzer.new(word, candidate).match?
    end
  end
end

class AnagramAnalyzer
  
  def initialize(word, candidate)
    @word       = normalize word
    @candidate  = normalize candidate
  end

  def match?
    @word == @candidate
  end

  private

  def normalize(word)
    word.downcase.chars.sort
  end
end
