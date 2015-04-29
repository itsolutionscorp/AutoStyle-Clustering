class Element
	
	attr_accessor :value, :next, :prev

	def initialize(value)
		@value = value
	end

	def insert(before, after)
		before.next = self
		self.prev = before
		self.next = after
		after.prev = self
		self
	end

	def remove
		self.prev.next = self.next
		self.next.prev = self.prev
		self
	end

end

class Deque
	def initialize
		self.sentinel = Element.new(nil)
	end

	def push(n)
		Element.new(n).insert(sentinel.prev, sentinel)
	end

	def pop
		sentinel.prev.remove.value
	end

end
