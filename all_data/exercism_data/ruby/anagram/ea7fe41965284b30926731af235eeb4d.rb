class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    Filter.execute(@word, words)
  end

end

class Filter
  def initialize(word, anagrams)
    @word, @anagrams = word.downcase, anagrams
  end

  def self.execute(word, anagrams)
    new(word, anagrams).execute
  end

  def execute
    @anagrams.delete_if(&duplicate).select(&anagram)
  end

  private

  def duplicate
    ->(other) { @word == other.downcase }
  end

  def anagram
    ->(other) { chiper(@word) == chiper(other.downcase) }
  end

  def chiper(element)
    Encoder.encode(element)
  end
end

class Encoder
  def self.encode(word)
    word.chars.sort.join
  end
end
