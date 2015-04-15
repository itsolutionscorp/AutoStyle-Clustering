class Phrase

  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    results = Hash.new(0)
    words.each { |word| results[word] += 1 }
    results
  end

  private
  def words
    sentence.gsub(/[\W_]+/, ' ').downcase.split(/[\s,]+/)
  end

end
