class Robot
	def reset
		@name = nil
	end

	def name
		@name ||= [('A'..'Z').to_a.shuffle[0,2].join, (0..9).to_a.shuffle[0,3].join].join		
		@name
	end
end
