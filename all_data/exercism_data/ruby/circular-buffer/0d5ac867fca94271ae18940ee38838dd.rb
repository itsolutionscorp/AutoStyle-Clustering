class CircularBuffer

  class BufferEmptyException < StandardError;  end;
  class BufferFullException < StandardError;   end;
  
  def initialize(size)
    @size = size
    @buffer = []
  end
  
  def write(data)
    raise CircularBuffer::BufferFullException if @buffer.size >= @size    
    @buffer << data if data
  end
  
  def write!(data)
    if data
      @buffer << data
      @buffer.shift
    end  
  end
    
  def clear
    @buffer = []
  end  
  
  def read
    data = @buffer.shift
    raise CircularBuffer::BufferEmptyException unless data
    data
  end

end
