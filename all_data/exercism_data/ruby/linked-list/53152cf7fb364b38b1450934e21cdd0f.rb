class Deque
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

  def initialize
    @head = Element.new(:sentinel, nil, nil)
  end

  def unshift(datum)
    Element.new(datum, head, head.succ).link
    self
  end

  def shift
    head.succ.unlink.datum
  end

  def push(datum)
    Element.new(datum, head.pred, head).link
    self
  end

  def pop
    head.pred.unlink.datum
  end

  private

  attr_reader :head
end
