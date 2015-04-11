class Phrase
  def initialize(words)
    @words = normalize(words)
  end

  def word_count
    Hash[@words.uniq.map { |word| [word, @words.count(word)] }]
  end

  private

  def normalize(words)
    words.downcase.split(/\W+/)
  end
end
