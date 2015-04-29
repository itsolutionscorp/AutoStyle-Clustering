class CustomSet
	def initialize(values = [])
		@elements = values.sort.uniq
	end
	
	def elements
		@elements
	end
	
	alias :to_a :elements
	
	def ==(other)
		other.elements == elements
	end
	
	def delete(element)
		@elements -= [element]
		self
	end
	
	def difference(other)
		self.class.new(elements - other.elements)
	end
	
	def disjoint?(other)
		self.difference(other) == self
	end
	
	def empty
		@elements = []
		self
	end
	
	def intersection(other)
		self.class.new(elements & other.elements)
	end
	
	def member?(value)
		@elements.select { |element| element == value && element.class == value.class }.any?
	end
	
	def put(element)
		@elements = (@elements + [element]).sort.uniq
		self
	end
	
	def size
		@elements.count
	end
	
	def subset?(other)
		self.intersection(other) == other
	end
	
	def union(other)
		self.class.new(elements | other.elements)
	end
end
