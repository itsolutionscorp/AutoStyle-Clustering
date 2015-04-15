
class CircularBuffer

  class BufferEmptyException < Exception
  end

  class BufferFullException < Exception
  end
  
  #I chose, per README description, that
  #the circular buffer will always remain at its specified length
  #and elements will not revert to nil after being read
  def initialize(size)
    @buffer = Array.new(size)
    @size = size
    @front = @end = @count = 0
  end

  def write(data)
    raise BufferFullException if buf_full?
    data.nil? ? return :  @buffer[@front] = data
    advance_front    
  end

  def write!(data)
    read if buf_full? #remove oldest valid entry
    write(data)
  end
  
  def read
    raise BufferEmptyException if buf_empty?
    @buffer[@end].tap { advance_end }  
  end

  def clear
    @front = @end = @count = 0    
  end

 private

  def buf_full?
    @size == @count  
  end

  def buf_empty?
    @count == 0 
  end

  def advance_front
    @front = (@front + 1 ) % @size
    @count += 1
  end

  def advance_end
    @end = (@end + 1) % @size
    @count -= 1
  end

end  

