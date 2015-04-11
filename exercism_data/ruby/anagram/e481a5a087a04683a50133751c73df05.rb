class Anagram
  def initialize(word)
    @word = word.downcase
    @normalized_word = normalize(word)
  end

  def match(words)
    words.select do |w|
      w = w.downcase
      @word != w and @normalized_word == normalize(w)
    end
  end

  def normalize(word)
    word.downcase.chars.sort
  end
end
