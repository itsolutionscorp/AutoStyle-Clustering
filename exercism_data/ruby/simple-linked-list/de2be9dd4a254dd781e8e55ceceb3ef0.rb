class Element
	attr_accessor :datum, :next

	def initialize(datum, nextElement = nil)
		@datum = datum
		@next = nextElement
	end

	def reverse
		head = nil
		each do |current|
			head = Element.new(current.datum, head)
		end
		head
	end

	def to_a
		self.class.to_a self
	end

	def self.to_a(element)
		element ? element.map(&:datum) : []
	end

	def self.from_a(a)
		head = nil
		a.each do |e|
			head = new(e, head)
		end
		head ? head.reverse : nil
	end

	def each(&block)
		current = self
		while current
			yield current
			current = current.next
		end
		self
	end

	def map(&block)
		result = []
		current = self
		while current
		 	result << (yield current)
		 	current = current.next
		end
		result
	end

end
