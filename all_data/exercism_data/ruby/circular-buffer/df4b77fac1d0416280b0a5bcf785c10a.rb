class CircularBuffer
  class BufferEmptyException < StandardError; end;
  class BufferFullException < StandardError; end;

  def initialize(buffer_size)
    @buffer_size = buffer_size
    @elements = []
  end

  def read
    if elements.empty?
      raise BufferEmptyException
    end

    elements.shift
  end

  def write(element)
    if is_full?
      raise BufferFullException
    end

    if element
      elements.push element
    end
  end

  def write!(element)
    read
    write(element)
  end

  def clear
    @elements = []
  end

  private

  attr_accessor :elements
  attr_reader :buffer_size

  def is_full?
    elements.size == buffer_size
  end
end
