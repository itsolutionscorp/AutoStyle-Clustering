class Phrase
	def initialize(phrase)
		@phrase = phrase 
	end

	def word_count
		@word_count ||= count_words
	end

  def word_count
    counts = Hash.new(0)
    @phrase.downcase.scan(/[\w']+/).each{ |word| counts[word] += 1 }
    counts
  end
end
