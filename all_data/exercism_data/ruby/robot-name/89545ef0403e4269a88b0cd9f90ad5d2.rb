class Robot

	def initialize
		@finished_name = ""
	end

	def name
		return @finished_name if @finished_name != ""
		new_name = []
		2.times { new_name << ('a'..'z').to_a.sample }
		3.times { new_name << (0..9).to_a.sample }

		@finished_name = new_name.join("").upcase
		@finished_name
	end

	def reset
		@finished_name = ""
	end

end
