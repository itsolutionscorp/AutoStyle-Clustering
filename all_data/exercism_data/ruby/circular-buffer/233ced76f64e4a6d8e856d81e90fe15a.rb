class CircularBuffer
  def initialize(size)
    @size = size
    clear
  end

  def write(value)
    raise BufferFullException if full?
    buffer << value if value
  end

  def write!(value)
    write(value)
  rescue BufferFullException
    read and retry
  end

  def read
    buffer.shift or
    raise BufferEmptyException
  end

  def clear
    @buffer = []
  end

  class BufferFullException < StandardError
  end

  class BufferEmptyException < StandardError
  end

  private
  attr_reader :size, :buffer

  def full?
    buffer.size == size
  end
end
