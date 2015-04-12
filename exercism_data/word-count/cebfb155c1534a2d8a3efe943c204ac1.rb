class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_hash = Hash.new(0)
    words = @phrase.downcase.scan(/\b[\w]+\b/)
    words.each { |word| word_hash[word] += 1 }
    return word_hash
  end

end
