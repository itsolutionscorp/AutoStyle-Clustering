module Value
  def self.included(base)
    base.extend(ClassMethods)
  end
  
  module ClassMethods
    
	def value(v)
		case v
		when 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'
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
	end
	
  end

  def value(v)
		case v
		when 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'
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
	end
  
  
end



class Scrabble
	include Value
    
	def initialize(word)
		@word = word 
	end

	def score
		if @word.nil? || @word.gsub(/\s/,"").empty?
			return 0
		end
		score = 0
		@word.split('').each do |letter|
			score = score + value(letter.upcase)
		end
		return score
	end

	def self.score(word)
		if word.nil? || word.gsub(/\s/,"").empty?
			return 0
		end
		score = 0
		word.split('').each do |letter|
			score = score + value(letter.upcase)
		end
		return score
	end
end
