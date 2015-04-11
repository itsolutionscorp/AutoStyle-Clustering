class Proverb
	def initialize(*args)
		@args = args
	end
	def to_s
		result = ""
		@args.each_cons(2) do |x, y|
			result << "For want of a #{x} the #{y} was lost.\n" unless y.class == Hash
		end
		if @args[-1].class == Hash
			result << "And all for the want of a #{@args[-1][:qualifier]} #{@args[0]}."
		else
			result << "And all for the want of a #{@args[0]}."
		end
		result
	end
end
