class CircularBuffer
  def initialize(size)
    @size = size
    @buf = Array.new(size)
    @r_iter = (0...size).cycle
    @w_iter = (0...size).cycle
    @ri = @r_iter.next 
    @wi = @w_iter.next
  end

  def read
    raise BufferEmptyException if !@buf.any?
    value = @buf[@ri]
    @buf[@ri] = nil
    @ri = @r_iter.next
    value
  end

  def write(value, force=false)
    return if value.nil?
    if @buf.all?
      raise BufferFullException if not force
      @ri = @r_iter.next
    end
    @buf[@wi] = value
    @wi = @w_iter.next
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
