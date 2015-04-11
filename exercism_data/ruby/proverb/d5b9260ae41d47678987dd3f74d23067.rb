class Proverb

	def initialize(*cons, qualifier: nil)
		@cons = cons
		@qualifier = qualifier
	end

	def to_s
		lines = @cons.each_cons(2).map do |cons1, cons2|
			"For want of a #{cons1} the #{cons2} was lost."
		end
		
		words = [@qualifier, @cons.first].compact.join " "
		lines << "And all for the want of a #{words}."
		lines.join("\n")
	end
	
end
