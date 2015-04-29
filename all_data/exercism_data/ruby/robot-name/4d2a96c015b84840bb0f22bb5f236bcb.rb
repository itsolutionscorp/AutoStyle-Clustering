class Robot
	attr_reader :name

	@@names = Array.new

	def initialize
		get_name
	end

	def get_name
		until !@@names.include?(@name) # Until we get a name that isn't already registered...
			@name = [*'A'..'Z'].sample + [*'A'..'Z'].sample + rand(0..9).to_s + rand(0..9).to_s + rand(0..9).to_s
		end
		@@names << @name
	end

	def reset
		get_name
	end

end
