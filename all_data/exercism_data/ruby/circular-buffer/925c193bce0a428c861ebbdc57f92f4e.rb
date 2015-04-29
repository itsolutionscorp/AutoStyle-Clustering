class CircularBuffer
	def initialize(size)
		@buffer = Array.new(size)
		@start = 0
		@size = 0
	end
	
	def read()
		raise(BufferEmptyException) unless @size > 0
		i = @start
		@start = (@start + 1) % @buffer.size()
		@size -= 1
		@buffer[i]
	end
	
	def write(data, force=false)
		return nil if data.nil?
		
		if @size >= @buffer.size()
			if force == true
				read()
			else
				raise(BufferFullException)
			end
		end
		
		i = (@start + @size) % @buffer.size()
		@size += 1
		@buffer[i] = data
		nil
	end
	
	def write!(data)
		write(data, true)
	end
	
	def clear()
		@size = 0
		nil
	end
	
end

class BufferEmptyException < Exception; end
class BufferFullException < Exception; end
