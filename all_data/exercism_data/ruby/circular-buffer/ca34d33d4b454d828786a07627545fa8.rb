class CircularBuffer
  class BufferEmptyException < StandardError;end
  class BufferFullException < StandardError;end

  attr_accessor :buffer, :top, :bottom, :size

  def initialize(size)
    @size = size
    clear
  end

  def write(datum)
    raise BufferFullException.new if buffer_full?
    return if datum.nil?
    buffer[top] = datum
    self.top += 1
    self.top = top % size
  end

  def write!(datum)
    return if datum.nil?
    buffer[bottom] = datum
    self.bottom += 1
    self.bottom = bottom % size
  end

  def read
    raise BufferEmptyException.new if position_empty?
    read = bottom
    self.bottom += 1
    self.bottom = bottom % size
    answer = buffer[read]
    buffer[read] = nil
    answer
  end

  def clear
    @buffer = Array.new(size, nil)
    @bottom = 0
    @top = 0
  end

  private

  def buffer_full?
    buffer.all?
  end

  def position_empty?
    buffer[bottom].nil?
  end
end
