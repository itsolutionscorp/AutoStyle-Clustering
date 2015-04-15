class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count words
  end

  private 

  def words
    normalized_phrase.scan(/\w+['a-z]*/)
  end

  def normalized_phrase
    @phrase.downcase
  end

  def count words
    words.inject(Hash.new(0)) {|result, word| result[word]+=1; result }
  end

end
