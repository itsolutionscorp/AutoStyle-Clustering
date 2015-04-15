class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each.with_object(Hash.new(0)) do |word|
      word_count[word.downcase] += 1
    end
  end
  
  def words
    phrase.scan(/\w+/)
  end
end
