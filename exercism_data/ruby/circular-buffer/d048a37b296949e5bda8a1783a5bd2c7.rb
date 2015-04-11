class CircularBuffer
  class BufferEmptyException < StandardError
  end

  class BufferFullException < StandardError
  end

  attr_reader :buffer

  def initialize n
    @buffer = []
    n.times { @buffer << [] }
  end

  def clear
    @buffer.map! { [] }
  end

  def read
    fail BufferEmptyException if @buffer.flatten.empty?
    r = @buffer.first.shift
    @buffer.rotate!
    r
  end

  def write str
    fail BufferFullException if @buffer.all? { |a| !a.empty? }
    @buffer[@buffer.index([])] << str unless str.nil?
  end

  def write! str
    if @buffer.any? &:empty?
      write str
    else
      @buffer[0] = [str]
      @buffer.rotate!
    end
  end
end
