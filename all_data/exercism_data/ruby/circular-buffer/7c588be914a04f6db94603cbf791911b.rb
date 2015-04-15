class CircularBuffer
  module Error
    class BufferEmptyException  < StandardError;end
    class BufferFullException   < StandardError;end
  end
  include Error

  def initialize(capacity)
    @capacity = capacity
    @buffer = Array.new(capacity)
    @top      = 0
    @bottom   = 0
    @elements = 0
  end

  def read
    raise BufferEmptyException if @elements == 0
    value = @buffer[@top]
    @buffer[@top] = nil
    @top = (@top + 1) % @capacity

    @elements = @elements - 1
    return value
  end

  def write(value)
    return if value.nil?
    raise BufferFullException if @elements == @capacity
    @buffer[@bottom] = value
    @bottom = (@bottom + 1) % @capacity
    @elements = @elements + 1
  end

  def write!(value)
    @buffer[@top] = value
    @top = (@top + 1) % @capacity if @elements == @capacity
  end

  def clear
    initialize(@capacity)
  end

end
