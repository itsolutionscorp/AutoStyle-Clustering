class Phrase

  def initialize sentence
  	@sentence = sentence
  end

  def word_count
	count words_and_numbers_stripped_of_punctuation
  end

  private

  def count words
    words.each_with_object Hash.new(0) do |word,word_counts| 
    	word_counts[word]+=1     
    end
  end

  def words_and_numbers_stripped_of_punctuation
      @sentence.downcase.scan /[[:alnum:]]+/  	  	
  end

end
