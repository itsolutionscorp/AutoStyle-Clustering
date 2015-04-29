class Robot

	def initialize
		@name = ''
	end

	def reset
		@name = ''
	end

	def name
		if @name.empty?
			@name =  ((0...2).map { (65 + rand(26)).chr } + (0...3).map{ rand(9) }).join
		end
		return @name
	end

end
	
