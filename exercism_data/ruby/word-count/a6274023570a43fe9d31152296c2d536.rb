class Phrase
  def initialize(words)
    @words = Hash.new(0)
    preprocess(words).each { |word| @words[word.downcase] += 1 }
  end

  def word_count
    @words
  end

  private

  def preprocess(words)
    words.gsub(/\W+\s*\W/, " ").gsub(/\./, "").split(/\s|,/)
  end
end
