#This seems to work perfectly well. I'm not entirely sure what I'd do to make it smoother. My thoughts so far are that it might make sense to separate out the cleaner function on line 13 from the counter function on line 15.

class Phrase 
	
	attr_accessor :words
	
	def initialize(words)
		@words = words
	end 
	
	def word_count
	counter = Hash.new()
	words = @words.strip.downcase.split(/[^A-Za-z0-9']+/)
		words.map{|word| 
			if counter.has_key?(word)
				counter[word] += 1
			else
				counter[word] = 1
			end
		}
		counter
	end
end


jumble = Phrase.new("car : carpet as java : javascript!!&@$%^&")
print jumble.word_count
