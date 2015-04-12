class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    get_list_of_words.each do |w|
      counts[w] += 1
    end
    counts
  end
 
  private
  
  def get_list_of_words
    @phrase.downcase.scan(/\w+/)
  end

end
