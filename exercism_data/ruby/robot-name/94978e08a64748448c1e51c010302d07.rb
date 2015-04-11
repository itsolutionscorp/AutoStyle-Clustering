require 'pry'
class Robot

	def name
		return @name unless @name.nil?
		@name = [*('A'..'Z')].sample(2).join + [*('0'..'9')].sample(3).join
	end

	def reset
		@name  = nil
	end

end
