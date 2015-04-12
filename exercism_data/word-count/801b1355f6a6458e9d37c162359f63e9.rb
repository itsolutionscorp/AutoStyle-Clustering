class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    tokenized_words.each_with_object(Hash.new(0)) do |word, results|
      results[word.downcase] += 1
    end
  end

  def tokenized_words
    @words.scan(/\w+/)
  end
end
