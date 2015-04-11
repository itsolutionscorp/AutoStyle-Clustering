class Phrase
  
  def initialize (phrase)
    @phrase = phrase
    @counted_words = Hash.new
  end

  def word_count
    @words_array = remove_clutter @phrase
    @words_array.uniq.each do |word|
      @counted_words[word] = @words_array.count(word)
    end
    @counted_words
  end
 
  private 

  def remove_clutter phrase
    phrase.downcase.gsub(/\W+/,' ').split
  end

end
