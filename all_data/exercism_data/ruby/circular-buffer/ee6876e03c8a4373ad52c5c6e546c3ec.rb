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
    buffer.shift and retry
  end

  def read
    buffer[current_read_index] or
    (reset_read_head and raise BufferEmptyException)
  end

  def clear
    @buffer = []
    reset_read_head
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

  def current_read_index
    read_head.next
  end

  def reset_read_head
    read_head.rewind
  end

  def read_head
    @read_head ||= (0..size).each
  end
end
