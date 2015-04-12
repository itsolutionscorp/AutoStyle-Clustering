class Phrase
  def initialize(text)
    @text = text
    @words = text.gsub(/[^a-zA-Z \d']/, ' ').downcase.split(" ")
  end
  def word_count
    word_counts = @words.map {|word| @words.count(word)}
    Hash[@words.zip(word_counts)]
  end
end
