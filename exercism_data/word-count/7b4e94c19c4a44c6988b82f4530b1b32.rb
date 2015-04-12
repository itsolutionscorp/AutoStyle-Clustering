class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    results = Hash.new(0)
    tokenized_words.each do |word|
      results[word.downcase] += 1
    end
    return results
  end

  def tokenized_words
    @words.gsub(/,/, ' ').
      gsub(/[^A-Za-z0-9 ]/, '').
      split(/\s+/)
  end
end
