class CircularBuffer
  class BufferEmptyException < StandardError; end
  class BufferFullException < StandardError; end
  def initialize length
    @length = length
    @read_head = @write_head = 0
    @buffer = Array.new @length
  end
  
  def write data
    fail BufferFullException if full?
    write! data
  end
  
  def write! data
    write_to_buffer data
    self
  end
  
  def read
    fail BufferEmptyException if empty?
    @buffer[@read_head].tap do
      @buffer[@read_head] = nil
      incr_read_head
    end
  end
  
  def clear
    @buffer = Array.new @length
  end
  
  private
  
  def write_to_buffer data
    return if data.nil?
    incr_read_head if overwriting?
    @buffer[@write_head] = data
    incr_write_head
  end
  
  def incr_read_head
    @read_head = (@read_head+1) % @length
  end
  
  def incr_write_head
    @write_head = (@write_head+1) % @length
  end
  
  def overwriting?
    full? && @write_head == @read_head
  end
  
  def empty?
    @buffer.compact.empty?
  end
  
  def full?
    @buffer.all?
  end
end
