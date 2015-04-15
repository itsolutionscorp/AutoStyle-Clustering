class CircularBuffer

  def initialize(size)
    @data = Array.new(size)
    @read_pos = ModCount.new(size)
    @write_pos = ModCount.new(size)
  end

  def write data
    raise BufferFullException if full?
    return unless data
    @data[@write_pos.to_i] = data
    @write_pos.inc
  end

  def write! data
    return unless data
    @read_pos.inc if @data[@write_pos.to_i]
    @data[@write_pos.to_i] = nil
    write data
  end

  def read
    raise BufferEmptyException if empty?
    res = @data[@read_pos.to_i]
    @data[@read_pos.to_i] = nil
    @read_pos.inc
    res
  end

  def clear
    @data = Array.new(@data.size)
    @write_pos.rewind
    @read_pos.rewind
  end

  def full?
    @data.all? { |b| !b.nil? }
  end

  def empty?
    @data.all? { |b| b.nil? }
  end

  class BufferEmptyException < Exception; end
  class BufferFullException < Exception; end

end

class ModCount
  def initialize(size)
    @size = size
    @value = 0
  end

  def rewind
    @value = 0
  end

  def inc
    @value = (@value + 1) % @size
  end

  def to_i
    @value
  end
end
