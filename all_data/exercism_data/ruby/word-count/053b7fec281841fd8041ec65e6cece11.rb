class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s
  end
  
  def word_count
    words.reduce(Hash.new(0)) { |hsh, word| hsh[word] += 1; hsh }
  end 
  
  protected
  
  def words
    @phrase.downcase.split(/\W+/)
  end
  
end
