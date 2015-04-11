class Anagram
  attr_reader :word
  def initialize(word)
    @word             = word.downcase
    @word_fingerprint = fingerprint(word)
  end

  def match(words)
    words.select do |w| 
      word_fingerprint == fingerprint(w) &&
      word != w.downcase
    end
  end

  private

  def word_fingerprint
    @word_fingerprint ||= fingerprint(word)
  end

  def fingerprint(word)
    word.downcase.chars.sort
  end

end
