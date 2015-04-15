class Phrase
	attr_reader :sentence

	def initialize(sentence)
		@sentence = sentence
	end

	def word_count
		results = Hash.new { |hash, key| hash[key] = 0 }
		words.each do |word|
			results[word] += 1
		end
		results
	end

	private
	def words
		sentence.split(/[\s,]/).map do |x|
			x.gsub(/[\W_]*/, '').downcase
		end.reject{|x| x==''}
	end
end
