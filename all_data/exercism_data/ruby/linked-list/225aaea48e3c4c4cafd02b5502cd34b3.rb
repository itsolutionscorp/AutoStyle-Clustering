class Deque
	attr_reader :size
	
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
		add(datum)
		nil
	end
	
	def unshift(datum)
		@front = add(datum) 
		nil
	end
	
	def pop()
		raise(LinkedListError, 'List is empty!') unless @front
		delete(@front.previous)
	end
	
	def shift()
		raise(LinkedListError, 'List is empty!') unless @front
		@front = @front.next
		pop()
	end
	
	private
	
	def add(datum)
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
		elem
	end
	
	def delete(elem)
		if elem == elem.next
			# only item in list...
			@front = nil
		else
			# update pointers
			elem.previous.next = elem.next
			elem.next.previous = elem.previous
		end
		
		elem.previous = nil #prolly not needed...
		elem.next = nil #prolly not needed...
		
		@size -= 1
		elem.datum
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
