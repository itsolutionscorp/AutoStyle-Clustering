class BufferEmptyException < StandardError; end
class BufferFullException < StandardError; end

class CircularBuffer
  attr_accessor :head

  def initialize(buffer_length)
    @buffer_length = buffer_length
  end

  def read
    raise BufferEmptyException if empty?

    datum = head.datum
    @head = head.link
    datum
  end

  def write(datum)
    raise BufferFullException if full?

    datum && push(datum)
  end

  def write!(datum)
    @head = head.link
    datum && push(datum)
  end

  def clear
    @head = nil
  end

  private

  def empty?
    !head
  end

  def full?
    node_count == @buffer_length
  end

  def push(datum)
    return @head = Node.new(datum) if empty?

    last = fast_forward
    last.link = Node.new(datum)
  end

  def fast_forward(&block)
    current = head
    while current.link
      current = current.link
      yield if block_given?
    end
    current
  end

  def node_count
    return 0 if empty?

    k = 1
    fast_forward { k += 1 }
    k
  end

end

class Node
  attr_accessor :datum, :link

  def initialize(datum, link=nil)
    @datum = datum
    @link = link
  end
end
