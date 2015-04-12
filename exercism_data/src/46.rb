class Phrase

	def initialize(phrase)
    @phrase = phrase
	end

  def word_count
  	@word_counts = @phrase.downcase.split(/\W+/).inject(Hash.new(0)) do |counts, word|
  		counts[word] += 1 
  		counts
  	end
  end

end
