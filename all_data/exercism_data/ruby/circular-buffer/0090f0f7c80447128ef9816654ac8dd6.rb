class CircularBuffer
	class BufferEmptyException < StandardError
	end
	class BufferFullException < StandardError
	end
	
	def initialize(size)
		@items = new_buffer(size)
		@read = 0
		@write = 0
	end
	
	def read
		raise BufferEmptyException if empty? || @items[@read].nil?
		value = @items[@read]
		@items[@read] = nil
		@read = cycle(@read)
		value
	end
	
	def write!(value)
		unless value.nil?
			@items[@read] = value
			@read = cycle(@read)
		end
	end
	
	def write(value)
		raise BufferFullException if full?
		unless value.nil?
			@items[@write] = value
			@write = cycle(@write)
		end
	end
	
	def clear
		@items = new_buffer(@items.size)
	end
	
private

	def new_buffer(size)
		(0...size).map{|i| nil}
	end

	def empty?
		@items.none?
	end
	
	def full?
		@items.all?
	end
	
	def cycle(idx)
		(idx + 1) % @items.length
	end
end
