class Phrase
  
  def initialize (phrase)
    @phrase = phrase
    @counted_words = Hash.new
  end

  def word_count
    phrase_no_interpunct = remove_clutter
    words_array = phrase_no_interpunct.split
    words_array_uniqued = words_array.uniq
    words_array_uniqued.each do |word|
      @counted_words[word] = words_array.count(word)
    end
    @counted_words
  end
 
private 
  def remove_clutter
    str = @phrase.downcase.gsub(/\W+/,' ')
  end
end
