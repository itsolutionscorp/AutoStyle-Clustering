class Robot
	def initialize
		@name = "" 
	end

	def name
		2.times.reduce(@name) do |acc|
		  acc << ('a'..'z').to_a[rand(26)].upcase
		end
		3.times.reduce(@name) do |acc|
			acc << Random.rand(1..9).to_s
		end
		@name
	end

	def reset
		@name = "" 
	end
end
