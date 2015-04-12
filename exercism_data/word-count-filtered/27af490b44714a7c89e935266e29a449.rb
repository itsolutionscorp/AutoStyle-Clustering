class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    words = @words.downcase.split(/\W+/)
    Hash[words.uniq.map { |word| [word, words.count(word)] }]
  end
end
