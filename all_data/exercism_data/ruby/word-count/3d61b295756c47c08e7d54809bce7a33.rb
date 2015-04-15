class Phrase

	def initialize(phrase)
    @phrase = phrase
	end

  def word_count
  	@word_counts = @phrase.downcase.split(/\W+/).inject({}) do |counts, word|
  		counts[word].nil? ? counts[word] = 1 : counts[word] += 1 
  		counts
  	end
  end

end
