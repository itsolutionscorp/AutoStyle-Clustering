class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    hash = Hash.new(0)
    words = @phrase.downcase.scan(/[\w']+/).each { |word| hash[word] += 1}
    hash
  end
end
