class CircularBuffer

  def initialize(buffer_size)
    @buffer = Array.new()
    @max_slots = buffer_size
  end

  def read
    raise BufferEmptyException if @buffer.size == 0
    @buffer.shift
  end

  def write(item)
    raise BufferFullException if (@buffer.size == @max_slots)
    unless item.nil?
      @buffer.push(item)
    end
  end

  def write!(item)
    unless item.nil?
      self.read
      self.write(item)
    end
  end

  def clear
    @buffer.size.times { @buffer.shift }
  end

end

class BufferEmptyException < RuntimeError

end

class BufferFullException < RuntimeError

end
