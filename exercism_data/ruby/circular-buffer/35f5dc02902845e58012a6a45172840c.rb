class CircularBuffer
  def initialize(size)
    @buffer = Array.new
    @size = size
  end

  def write(data)
    if @buffer.length == @size
      raise BufferFullException
    elsif !data.nil?
      @buffer.push(data)
    end
  end

  def write!(data)
    @buffer.shift
    @buffer.push(data)
  end

  def read
    if @buffer.empty?
      raise BufferEmptyException
    else
      @buffer.shift
    end
  end

  def clear
    @buffer = Array.new
  end

  class CircularBuffer::BufferEmptyException < StandardError
  end

  class CircularBuffer::BufferFullException < StandardError
  end
end
