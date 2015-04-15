class Element
	
	attr_accessor :value, :next, :prev

	def initialize(value)
		@value = value
	end

end

class Deque
	attr_accessor :size

	def initialize
		@size = 0
	end

	def push(n)
		new_element = Element.new(n)
		case @size
		when 0
			@first = @last = new_element
		when 1
			@last = new_element
			@last.prev = @first
			@first.next = @last
		else
			@last.next = new_element
			new_element.prev = @last
			@last = new_element
		end
		@size += 1
		self
	end

	def pop
		case @size
		when 0
			nil
		when 1
			n = @last.value
			@first = nil
			@last = nil
			@size -= 1
			n
		else
			n = @last.value
			@last = @last.prev
			@last.next = nil
			@size -= 1
			n
		end
	end

	def unshift(n)
		new_element = Element.new(n)
		case @size
		when 0
			@first = @last = new_element
		when 1
			@first = new_element
			@last.prev = @first
			@first.next = @last
		else
			new_element.next = @first
			@first.prev = new_element
			@first = new_element
		end
		@size += 1
		self
	end

	def shift
		case @size
		when 0
			nil
		when 1
			n = @first.value
			@first = nil
			@last = nil
			@size -= 1
			n
		else
			n = @first.value
			@first = @first.next
			@first.prev = nil
			@size -= 1
			n
		end
	end

end
