class CircularBuffer
  class BufferEmptyException < StandardError; end
  class BufferFullException < StandardError; end

  class Element
    attr_accessor :pred, :succ
    attr_reader :datum

    def initialize(datum, pred, succ)
      @datum = datum
      @pred  = pred || self
      @succ  = succ || self
    end

    def unlink
      pred.succ = succ
      succ.pred = pred
      self
    end

    def link
      pred.succ = self
      succ.pred = self
      self
    end
  end

  def initialize(max_size)
    @max_size = max_size
    clear
  end

  def read
    raise BufferEmptyException if buffer_empty?
    self.size -= 1
    head.succ.unlink.datum
  end

  def write(datum)
    raise BufferFullException if buffer_full?
    if datum
      self.size += 1
      Element.new(datum, head.pred, head).link
    end
    self
  end

  def write!(datum)
    write(datum)
  rescue BufferFullException
    read
    retry
  end

  def clear
    self.size = 0
    self.head = Element.new(:sentinel, nil, nil)
    self
  end

  private

  attr_reader :max_size
  attr_accessor :head, :size

  def buffer_empty?
    size == 0
  end

  def buffer_full?
    size == max_size
  end
end
