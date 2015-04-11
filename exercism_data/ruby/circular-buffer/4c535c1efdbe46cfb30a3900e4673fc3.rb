class CircularBuffer
  def initialize(size)
    @size = size
    @buf = Array.new(size)
    @ri = 0
    @wi = 0
  end

  def read
    raise BufferEmptyException if !@buf.any?
    value = @buf[@ri]
    @buf[@ri] = nil
    @ri = (@ri + 1) % @buf.size
    value
  end

  def write(value, force=false)
    return if value.nil?
    if @buf.all?
      raise BufferFullException if not force
      @ri = (@ri + 1) % @buf.size
    end
    @buf[@wi] = value
    @wi = (@wi + 1) % @buf.size
    value
  end

  def write!(value)
    write(value, force=true)
  end

  def clear
    @buf = [nil] * @size
  end

  class BufferEmptyException < RuntimeError
  end

  class BufferFullException < RuntimeError
  end
end
