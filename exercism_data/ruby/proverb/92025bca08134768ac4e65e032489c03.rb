class Proverb

	def initialize(*cons, qualifier: "")
		@cons = cons
		@qualifier = qualifier
	end

	def to_s
		lines = @cons.each_cons(2).map do |cons1, cons2|
			"For want of a #{cons1} the #{cons2} was lost."
		end
		
		words = (@qualifier + " " + @cons[0]).strip
		lines << "And all for the want of a #{words}."
		lines.join("\n")
	end
	
end
