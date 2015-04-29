class Robot
	
	def initialize
		@name = nil
	end
	
	ABC = %w{ A B C D E F G H I J K L M N O P Q R S T U V W X Y Z }
	
	def name
		unless @name
			char1 = rand(26)
			char2 = rand(26)
			num = ( rand(999999) / 1000 ).to_int
			@name = ABC[char1 - 1] + ABC[char2 - 1] + num.to_s 
		end
		@name
	end
	
	def reset
		@name = nil
	end
end
