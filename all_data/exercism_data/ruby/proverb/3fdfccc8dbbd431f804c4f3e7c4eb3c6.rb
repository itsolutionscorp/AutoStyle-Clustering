class Proverb
	def initialize(*args)
		@list = args
		@qualifier = args.pop[:qualifier] if args.last.is_a? Hash
	end
	def to_s
		string = ""
		(0...@list.length-1).each do |a|
			string += "For want of a #{@list[a]} the #{@list[a.next]} was lost.\n"
		end
		return string += "And all for the want of a #{completion}."
	end
	def completion
		@qualifier? 'horseshoe nail' : @list.first
	end
end
