class Phrase
  def initialize(words)
    @words = clean(words)
  end

  def word_count
    Hash[@words.uniq.map { |word| [word, @words.count(word)] }]
  end

  private

  def clean(w)
    w.downcase.split(/\W+/)
  end
end
