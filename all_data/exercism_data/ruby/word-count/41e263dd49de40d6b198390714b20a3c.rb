class Phrase
  attr_reader :words
  def initialize(words)
    @words = words
  end

  def word_count
    words.scan(/\w+/).each_with_object(Hash.new(0)) do |word, counts|
      word = normalize_case(word)
      counts[word] += 1
    end
  end

  def normalize_case(word)
    word.downcase
  end
end
