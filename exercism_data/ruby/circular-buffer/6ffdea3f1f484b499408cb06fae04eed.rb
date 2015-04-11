class CircularBuffer
  def initialize(n)
    @n = n
    @read_point, @write_point = 0, 0
    @buffer = Array.new(n)
  end

  def write(datum)
    fail BufferFullException if full?

    update(datum) { @buffer[@write_point] = datum }
  end

  def write!(datum)
    update(datum) do
      @buffer[@write_point] = datum
      update_read_point if overwrite?
    end
  end

  def read
    fail BufferEmptyException if empty?

    datum = @buffer[@read_point]
    @buffer[@read_point] = nil
    update_read_point

    datum
  end

  def clear
    initialize(@n)
  end

  class BufferEmptyException < Exception; end
  class BufferFullException < Exception; end

  private

  def overwrite?
    (full? && @write_point == @read_point)
  end

  def empty?
    @buffer.compact.empty?
  end

  def full?
    @buffer.all?
  end

  def update(datum)
    return if datum.nil?
    yield
    update_write_point
  end

  def update_write_point
    @write_point = (@write_point + 1) % @n
  end

  def update_read_point
    @read_point = (@read_point + 1) % @n
  end
end
