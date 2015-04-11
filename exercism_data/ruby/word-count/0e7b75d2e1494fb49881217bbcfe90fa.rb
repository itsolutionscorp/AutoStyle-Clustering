class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def words
    @phrase.downcase.scan(/\w+/)
  end

  def word_count
    words.inject(Hash.new(0)) do |hash, word|
      hash[word] += 1
      hash
    end
  end

end
