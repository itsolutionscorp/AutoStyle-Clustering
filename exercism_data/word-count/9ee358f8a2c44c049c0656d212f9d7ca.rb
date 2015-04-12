class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    # using inject building the counts hash
    words.inject(Hash.new(0)) do | counts, word |
      counts[word] += 1 
      counts
    end
  end
 
  private
  
  def words
    @phrase.downcase.scan(/\w+/)
  end

end
