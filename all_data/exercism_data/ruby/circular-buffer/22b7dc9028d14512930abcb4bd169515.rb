class CircularBuffer

	def initialize(length)
		@buffer = Array.new(length)
		@pointer = 0
		@last_written_index = nil
	end
	
	def read
		empty = true
		@buffer.each {|b| empty = false if b != nil}
		if empty
			raise BufferEmptyException.new
		else 
			return_value = @buffer[@pointer]
			@buffer[@pointer] = nil
			empty = true
			@buffer.each {|b| empty = false if b != nil}
			if empty
					@pointer = 0
					@last_written_index = nil
			else 	@pointer = (@pointer + 1) % @buffer.length 
			end
			return_value
		end
	end
	
	def next_writable_index
		if @last_written_index != nil
			(@last_written_index + 1) % @buffer.length
		else 0
		end		
	end
	
	def write(number)
		unless number == nil
			next_writable_index = self.next_writable_index	
			
			if  @buffer[next_writable_index] == nil
				@buffer[next_writable_index] = number
				@last_written_index = next_writable_index
			else	
				raise BufferFullException.new
			end
		end
	end
	
	def write!(number)
		unless number == nil
			next_writable_index = self.next_writable_index	
			
			if  @buffer[next_writable_index] == nil
				@buffer[next_writable_index] = number
				@last_written_index = next_writable_index
			else
				@buffer[@pointer] = number
				@last_written_index = @pointer
				@pointer = (@pointer + 1) % @buffer.length
			end
		end
	end
	
	def clear
		@buffer.map! {|b| nil}
	end
end

 class BufferEmptyException < Exception
 end
 
 class BufferFullException < Exception
 end
