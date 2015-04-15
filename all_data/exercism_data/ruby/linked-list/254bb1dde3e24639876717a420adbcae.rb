class Element
	
	attr_accessor :value, :next, :prev

	def initialize(value)
		@value = value
		@next = @prev = self
	end

	def link(before, after)
		before.next = self
		self.prev = before
		self.next = after
		after.prev = self
		self
	end

	def unlink
		self.prev.next = self.next
		self.next.prev = self.prev
		self
	end

end

class Deque

	def initialize
		@sentinel = Element.new(nil)
	end

	def push(n)
		Element.new(n).link(@sentinel.prev, @sentinel)
		self
	end

	def pop
		@sentinel.prev.unlink.value
	end

	def unshift(n)
		Element.new(n).link(@sentinel, @sentinel.next)
		self
	end

	def shift
		@sentinel.next.unlink.value
	end

end
