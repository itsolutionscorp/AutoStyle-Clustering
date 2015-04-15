class Phrase
  def initialize(phrase)
    keys = phrase.downcase.split(/[^a-zA-Z0-9\']+/)
    counts = keys.map { |k| keys.count(k) }

    @word_count = Hash[keys.zip(counts)]
  end

  def word_count
    @word_count
  end
end
