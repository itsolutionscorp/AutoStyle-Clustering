class Element
	#first iteration and last three tests fails
 
	attr_accessor :datum, :next
	def initialize(datum, link)
		@datum = datum
		@next = link
	end

	def self.to_a(arg)
		return [] if arg == nil
		result = []
		if arg.instance_of? Element
			result << arg.datum
			result << arg.next.datum unless arg.next == nil
		end
		result
	end

	def self.from_a(arg)
		return nil if arg == []
		Element.new(arg[0],arg[1])
	end

	def reverse
		if self.next.instance_of? Element
			Element.new(self.next, self.datum)
		else
			Element.new(self.datum, self.next)
		end
	end
end
