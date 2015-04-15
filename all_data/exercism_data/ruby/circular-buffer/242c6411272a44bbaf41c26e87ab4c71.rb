class CircularBuffer
	class BufferEmptyException < Exception; end
	class BufferFullException  < Exception; end

	def initialize(size)
		setup(size)
	end

	def setup(size)
		@buffer = Array.new(size)
		@elements = 0
		@oldest = 0
	end

	def clear
		setup(@buffer.size)
	end

	def read
		raise BufferEmptyException if @elements == 0

		info = @buffer[@oldest]
		@elements -= 1
		next_oldest
		info
	end

	def write(info)
		raise BufferFullException if full?
		
		return self if info.nil?

		non_full_write(info)
		self
	end

	def write!(info)
		return self if info.nil?

		full? ? full_write(info) : non_full_write(info)
		self
	end

	def full?
		@elements == @buffer.size
	end

	private

		def non_full_write(info)
			@buffer[next_writeable_spot] = info
			@elements += 1
		end

		def full_write(info)
			@buffer[@oldest] = info
			next_oldest
		end

		def next_writeable_spot
			(@elements + @oldest) % @buffer.size
		end

		def next_oldest
			@oldest = (@oldest + 1) % @buffer.size
		end


end
