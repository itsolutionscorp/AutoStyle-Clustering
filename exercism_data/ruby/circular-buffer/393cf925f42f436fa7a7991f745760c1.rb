class CircularBuffer
  class BufferEmptyException < StandardError; end
  class BufferFullException < StandardError; end

  def initialize(size)
    @size = size
    clear
  end

  def read
    raise BufferEmptyException if buffer_empty?
    buffer.shift
  end

  def write(datum)
    raise BufferFullException if buffer_full?
    buffer.push(datum) if datum
    self
  end

  def write!(datum)
    write(datum)
  rescue BufferFullException
    read
    retry
  end

  def clear
    @buffer = []
  end

  private

  attr_reader :buffer, :size

  def buffer_empty?
    buffer.empty?
  end

  def buffer_full?
    buffer.size == size
  end
end
