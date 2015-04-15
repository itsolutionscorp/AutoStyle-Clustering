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
    return unless datum

    push(datum)
  end

  def write!(datum)
    @head = head.link
    push(datum)
  end

  def clear
    @head = nil
  end

  private

  def empty?
    !head
  end

  def full?
    return node_count == @buffer_length
  end

  def push(datum)
    return @head = Node.new(datum) unless head

    last = fast_forward
    last.link = Node.new(datum)
  end

  def fast_forward(&block)
    node = head
    while node.link
      node = node.link
      yield if block_given?
    end
    node
  end

  def node_count
    return 0 if !head
    k = 1
    fast_forward { k += 1 }
    k
  end

end

class Node
  attr_reader :datum, :link

  def initialize(datum, link=nil)
    @datum = datum
    @link = link
  end

  def link=(node)
    @link = node
  end

  def datum=(datum)
    @datum = datum
  end

end

class BufferEmptyException < StandardError; end
class BufferFullException < StandardError; end
