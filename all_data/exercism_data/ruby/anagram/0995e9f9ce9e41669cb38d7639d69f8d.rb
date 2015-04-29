class Anagram
  def initialize(words)
    @words = analyze(words)
  end

  def match(words)
    words.select do |word|
      analyze(word) == @words
    end
  end

  def analyze(letters)
    letters.chars.sort
  end
end
