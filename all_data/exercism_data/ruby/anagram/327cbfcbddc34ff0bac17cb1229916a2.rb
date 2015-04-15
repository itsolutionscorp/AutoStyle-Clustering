class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select do |word|
      @word.downcase != word.downcase && normalize(word) == normalized_anagram
    end
  end

  private
  def normalized_anagram
    @normalized_anagram ||= normalize(@word)
  end

  def normalize(word)
    word.downcase.split("").sort
  end
end
