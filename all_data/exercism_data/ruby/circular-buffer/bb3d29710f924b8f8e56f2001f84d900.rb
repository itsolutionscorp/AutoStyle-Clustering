class CircularBuffer

  def initialize(limit)
    @limit = limit
    @buffer = []
  end

  def read
    raise BufferEmptyException if @buffer.empty?
    @buffer.shift
  end

  def write(element)
    if element == nil
      return 0
    elsif @buffer.length >= @limit
      raise BufferFullException
    else
      @buffer << element
    end
  end

  def write!(element)
    @buffer.shift
    @buffer << element
  end

  def clear
    @buffer.clear 
  end
end

class BufferEmptyException < Exception
end

class BufferFullException < Exception
end
