class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = {}
    get_list_of_words.each do |w|
      counts[w] = counts[w].nil? ? 1 : counts[w] + 1
    end
    counts
  end
 
  private
  
  def get_list_of_words
    @phrase.downcase.scan(/\w+/)
  end

end
