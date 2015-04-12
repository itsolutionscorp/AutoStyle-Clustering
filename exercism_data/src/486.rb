class Phrase
  def initialize(words)
    @words = words.downcase.delete('^a-z0-9 ').split
  end

  def word_count
    Hash[@words.uniq.map { |word| [word, @words.count(word)] }]
  end
end
