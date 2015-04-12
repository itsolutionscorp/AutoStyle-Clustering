class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.downcase.split(/[\W]+/)
    words.each_with_object(Hash.new(0)) {|word, result| result[word] += 1}
  end
end
