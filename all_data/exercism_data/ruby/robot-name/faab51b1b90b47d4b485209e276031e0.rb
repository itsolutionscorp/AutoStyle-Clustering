class Robot
	def name
		@name ||= new_name
	end
	def reset
		@name = nil
	end

	private 
	def new_name
		(65..90).to_a.sample(2).map(&:chr).
		push(*(0..9).to_a.sample(3)).
		join
	end
end
