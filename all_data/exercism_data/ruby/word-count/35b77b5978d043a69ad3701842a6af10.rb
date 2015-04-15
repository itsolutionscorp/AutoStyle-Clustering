class Phrase
  def initialize(words)
    @words = words.downcase.split(/\W+/)
  end

  def word_count
    Hash[@words.uniq.map { |word| [word, @words.count(word)] }]
  end
end
