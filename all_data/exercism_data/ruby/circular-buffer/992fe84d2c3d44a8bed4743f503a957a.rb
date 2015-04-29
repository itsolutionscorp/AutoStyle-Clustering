class CircularBuffer
	class BufferEmptyException < Exception; end
	class BufferFullException  < Exception; end

	def initialize(size)
		# setup(size)
		@size = size
		@buffer = []
	end

	def clear
		@buffer.clear
	end

	def read
		raise BufferEmptyException if @buffer.size == 0

		@buffer.shift
	end

	def write(info)
		raise BufferFullException if full?
		
		return self if info.nil?

		buffer << info
		self
	end

	def write!(info)
		return self if info.nil?

		buffer.shift if full?
		buffer << info

		self
	end

	def full?
		@buffer.size >= @size
	end

end
