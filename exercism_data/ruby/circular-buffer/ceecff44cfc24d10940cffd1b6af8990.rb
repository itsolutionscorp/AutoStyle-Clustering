class CircularBuffer
  def initialize(buffer_length)
    @buffer_length = buffer_length
    clear
  end

  def clear
    @buffer = []
  end

  def read
    raise BufferEmptyException if @buffer.empty?
    @buffer.shift
  end

  def write(datum)
    raise BufferFullException if @buffer.length == @buffer_length
    @buffer << datum if datum
  end

  def write!(datum)
    @buffer.shift
    @buffer << datum
  end
end

class BufferEmptyException < StandardError; end
class BufferFullException < StandardError; end
