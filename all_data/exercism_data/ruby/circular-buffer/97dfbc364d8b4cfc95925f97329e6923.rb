BufferFullException = Class.new(StandardError)
BufferEmptyException = Class.new(StandardError)

Node = Struct.new(:datum, :link)

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

    last.link = Node.new(datum)
  end

  def each(&block)
    current = head
    while current.link
      current = current.link
      yield if block_given?
    end
    current
  end

  def last
    each
  end

  def node_count
    return 0 if empty?

    k = 1
    each { k += 1 }
    k
  end

end
