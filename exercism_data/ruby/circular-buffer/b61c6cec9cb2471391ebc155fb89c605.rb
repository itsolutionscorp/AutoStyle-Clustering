class CircularBuffer
  def initialize(size)
    @array = Array.new(size)
    @write = @read = 0
  end

  def write(datum)
    write_with_handling(datum){raise BufferFullException}
  end

  def write!(datum)
    write_with_handling(datum){@read = increment(@read)}
  end

  def write_with_handling(datum, &block)
    if datum
      block.call if @array[@write]
      @array[@write] = datum
      @write = increment(@write)
    end
  end

  def read
    raise BufferEmptyException if @array.compact.empty?
    to_return = @array[@read]
    @array[@read] = nil
    @read = increment(@read)
    to_return
  end

  def clear
    @array.map!{|val|nil}
  end

  private
  def increment(num)
    (num + 1) % @array.size
  end

  class BufferEmptyException < StandardError; end
  class BufferFullException < StandardError; end
end
