class Phrase

  def initialize sentence
  	@sentence = sentence
  end

  def word_count
	count words_and_numbers_stripped_of_punctuation
  end

  private

  def count words
    words.inject Hash.new(0) do |h, w| 
    	h[w]+=1 
    	h
    end
  end

  def words_and_numbers_stripped_of_punctuation
      @sentence.downcase.scan /[[:alnum:]]+/  	  	
  end

end
