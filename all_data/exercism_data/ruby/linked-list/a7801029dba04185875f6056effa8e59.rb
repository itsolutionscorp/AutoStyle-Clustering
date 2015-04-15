class Deque
	attr_reader :size, :front
	
	def initialize
		@front = nil
		@size = 0
	end
	
	def to_a()
		current = @front
		[].tap do |result|
			@size.times do
				result << current.datum
				current = current.next
			end
		end
	end
	
	def reverse()
		current = @front
		[].tap do |result|
			@size.times do
				current = current.previous
				result << current.datum
			end
		end
	end
	
	def push(datum)
		elem = Element.new(datum)
		
		if @front
			elem.next = @front
			elem.previous = @front.previous
			
			elem.previous.next = elem
			elem.next.previous = elem
		else
			elem.previous = elem
			elem.next = elem
			@front = elem 
		end
		
		@size += 1
	end
	
	def pop()
		raise(LinkedListError, 'List is empty!') unless @front
		
		elem = @front.previous
		
		if @front == @front.next
			# only item in list...
			@front = nil
		else
			# update pointers
			elem.previous.next = elem.next
			elem.next.previous = elem.previous
			elem.previous = nil #prolly not needed...
			elem.next = nil #prolly not needed...
		end
		
		@size -= 1
		return elem.datum
	end
	
	def unshift(datum)
		elem = Element.new(datum)
		
		if @front
			elem.next = @front
			elem.previous = @front.previous
			
			elem.previous.next = elem
			elem.next.previous = elem
		else
			elem.previous = elem
			elem.next = elem
		end
		
		@front = elem 
		@size += 1
	end
	
	def shift()
		raise(LinkedListError, 'List is empty!') unless @front
		
		elem = @front
		
		if @front == @front.next
			# only item in list...
			@front = nil
		else
			# update pointers
			@front = @front.next
			
			elem.previous.next = elem.next
			elem.next.previous = elem.previous
			elem.previous = nil #prolly not needed...
			elem.next = nil #prolly not needed...
		end
		
		@size -= 1
		return elem.datum
	end
end

class Element
	attr_accessor :datum, :previous, :next
	
	def initialize(datum, previous_element=nil, next_element=nil)
		@datum = datum
		@previous = previous_element
		@next = next_element
	end
end

class LinkedListError < StandardError; end
