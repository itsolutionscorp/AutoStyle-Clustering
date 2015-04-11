class CircularBuffer
  class BufferEmptyException < Exception; end
  class BufferFullException < Exception; end

  def initialize(size)
    @buffer = Array.new(size)
    @size = size
    @used_spots = 0
    @write_pointer = @read_pointer = 0
  end

  def read
    raise BufferEmptyException if @used_spots.zero?

    value = @buffer[@read_pointer]
    @used_spots -= 1
    @read_pointer = advance_pointer(@read_pointer)
    value
  end

  def write(value)
    return if value.nil?
    raise BufferFullException if @used_spots >= @size

    @buffer[@write_pointer] = value
    @write_pointer = advance_pointer(@write_pointer)
    @used_spots += 1
  end

  def write!(value)
    read
    write(value)
  end

  def clear
    @used_spots = 0
    @write_pointer = @read_pointer = 0
  end

  private

  def advance_pointer(index)
   (index + 1) % @size
  end
end
