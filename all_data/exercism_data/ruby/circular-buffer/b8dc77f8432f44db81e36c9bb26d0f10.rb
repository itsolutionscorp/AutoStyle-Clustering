class CircularBuffer

  def initialize(buffer_size)
    @buffer = Array.new()
    @max_slots = buffer_size
  end

  def read
    raise BufferEmptyException if @buffer.empty?
    @buffer.shift
  end

  def write(item)
    raise BufferFullException if (@buffer.size == @max_slots)
    @buffer.push(item) unless item.nil?
  end

  def write!(item)
    unless item.nil?
      self.read
      self.write(item)
    end
  end

  def clear
    @buffer.clear
  end

end

class BufferEmptyException < Exception

end

class BufferFullException < Exception

end
