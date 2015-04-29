class Phrase
	def initialize(phrase)
		@phrase = phrase
	end

  def word_count
    count = Hash.new(0)
    words = @phrase.downcase.scan(/([\w']+)/).flatten
    words.each do |word|
      count[word] += 1
    end
    count
  end
end
