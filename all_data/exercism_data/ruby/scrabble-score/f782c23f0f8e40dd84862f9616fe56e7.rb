class Scrabble

	def initialize(word)
		@word = word
	end
	
	@@abc = {}
	range = 'A'..'Z'
	range.each do |character|
		value =
			case character
				when 'A','E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'
				1
				when 'D', 'G'                               
				2
				when 'B', 'C', 'M', 'P'                         
				3
				when 'F', 'H', 'V', 'W', 'Y'                      
				4
				when 'K'                                  
				5
				when 'J', 'X'                               
				8
				when 'Q', 'Z'                               
				10
			end
		hash = {character => value}
		@@abc.merge!(hash)
	end
	
	
	def score
		score = 0
		if @word != nil then
			@word.gsub!(/[\s\d]/, '')
			for i in 0...@word.length do
				score += @@abc[@word[i].upcase] 
			end
		else score = 0
		end
		score
	end
	
	def Scrabble.score(word)
		Scrabble.new(word).score
	end

end
