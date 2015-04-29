class CircularBuffer

  class BufferEmptyException < StandardError; end
  class BufferFullException < StandardError; end

  def initialize(size)
    @size = size
    @buffer = []
  end

  def read
    raise BufferEmptyException if @buffer.empty?
    @buffer.shift
  end

  def write(item)
    raise BufferFullException if @buffer.size == @size
    @buffer.push(item) unless item.nil?
  end

  def write!(item)
    @buffer.push(item)
    @buffer.shift
  end

  def clear
    @buffer = []
  end
end
