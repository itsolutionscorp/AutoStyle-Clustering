class CircularBuffer
  class BufferEmptyException < RuntimeError; end
  class BufferFullException < RuntimeError; end

  def initialize(size)
    @size = size
    @buffer = []
  end

  def read
    @buffer.shift or fail BufferEmptyException
  end

  def write(datum)
    fail BufferFullException unless @buffer.size < @size
    @buffer.push(datum) unless datum.nil?
  end

  def write!(datum)
    @buffer.push(datum).shift unless datum.nil?
  end

  def clear
    @buffer.clear
  end
end
