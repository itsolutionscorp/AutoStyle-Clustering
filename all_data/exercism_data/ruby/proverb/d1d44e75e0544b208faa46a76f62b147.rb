class Proverb
	attr_reader :words

	def initialize(*args, qualifier: "")
		@words = args.each.to_a
		@qualifier = qualifier
	end

	def to_s
		output = ""
		(@words.length-1).times do |x|
			output += "For want of a #{@words[x]} the #{@words[x+1]} was lost.\n"
		end
		@qualifier.empty? ? @qualifier : @qualifier += " "
		output += "And all for the want of a #{@qualifier}#{@words.first}."
	end
end
