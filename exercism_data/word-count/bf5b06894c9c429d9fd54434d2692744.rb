class Phrase
  def initialize(phrase)
    @filtered_words = phrase.downcase.split(/[^(\w|')]+/)
  end

  def word_count
    @filtered_words.each_with_object(Hash.new(0)) { | word, count | count[word] += 1 }
  end
end
