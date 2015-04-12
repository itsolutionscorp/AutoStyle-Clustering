class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = Hash.new
    words = @phrase.downcase.split(/[\W]+/)
    words.each { |word| result[word] = words.count(word) }
    result
  end
end
