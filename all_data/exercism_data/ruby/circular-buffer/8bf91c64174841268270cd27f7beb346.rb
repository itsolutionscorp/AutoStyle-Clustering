class CircularBuffer
  def initialize(size)
    @buffer    = Array.new(size)
    @size      = size
    @shift_idx = 0
    @push_idx  = 0
  end

  def write(input)
    raise BufferFullException if buffer_full?
    record(input, overwrite: false)
  end

  def write!(input)
    record(input, overwrite: true) if buffer_full?
  end

  def record(input, overwrite: false )
    return if input.nil?
    if overwrite
      @buffer[@shift_idx % @size] = input
      @shift_idx += 1
    else
      @buffer[@push_idx % @size] = input
      @push_idx += 1
    end
    self
  end

  def read
    raise BufferEmptyException if @buffer.compact.empty?
    tmp = @buffer[@shift_idx % @size]
    @buffer[@shift_idx % @size] = nil
    @shift_idx += 1
    tmp
  end

  def clear
    @buffer.map!{ |e| e = nil }
  end

  private

  def buffer_full?
    @buffer.none? { |e| e == nil }
  end

  class BufferEmptyException < StandardError; end
  class BufferFullException < StandardError; end
end
