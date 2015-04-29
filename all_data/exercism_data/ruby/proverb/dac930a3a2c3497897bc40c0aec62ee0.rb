class Proverb
	def initialize(*args)
		@list = args
	end
	def to_s
		string = ""
		(0...@list.length-1).each do |a|
			string += "For want of a #{@list[a]} the #{@list[a.next]} was lost.\n"
		end
		puts string += "And all for the want of a #{@list.first}."
	end
end
