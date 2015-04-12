class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    normalized_words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  def normalized_words
    @words.downcase.scan(/\w+/)
  end
end
