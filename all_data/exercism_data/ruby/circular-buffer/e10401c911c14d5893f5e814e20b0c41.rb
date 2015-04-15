class CircularBuffer
  def initialize(size)
    @buffer    = Array.new(size)
    @size      = size
    @read      = 0
    @write     = 0
  end

  def write(input)
    raise BufferFullException if buffer_full?
    record(input, overwrite: false)
  end

  def write!(input)
    record(input, overwrite: true) if buffer_full?
  end

  def record(input, overwrite: false )
    return self if input.nil?
    if overwrite
      @buffer[@read] = input
      @read = cycle(@read)
    else
      @buffer[@write] = input
      @write = cycle(@write)
    end
    self
  end

  def read
    raise BufferEmptyException if @buffer.compact.empty?
    tmp = @buffer[@read]
    @buffer[@read] = nil
    @read = cycle(@read)
    tmp
  end

  def clear
    @buffer.map!{ |e| e = nil }
  end

  private

  def buffer_full?
    @buffer.none? { |e| e == nil }
  end

  def cycle(idx)
    (idx + 1) % @size
  end

  class BufferEmptyException < StandardError; end
  class BufferFullException < StandardError; end
end
