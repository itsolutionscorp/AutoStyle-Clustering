class Element
	attr_reader :datum, :next
	
	def initialize(datum, next_element)
		@datum = datum
		@next = next_element
	end
	
	def self.to_a(elem)
		return [] unless elem.instance_of?(self)
		[elem.datum] + self.to_a(elem.next)
	end
	
	def self.from_a(arr)
		current = nil
		arr.reverse_each{|d| current = new(d, current)}
		current
	end
	
	def to_a()
		self.class.to_a(self)
	end
	
	def reverse(previous = nil)
		elem = self.class.new(@datum, previous)
		(@next) ? @next.reverse(elem) : elem
	end
end
