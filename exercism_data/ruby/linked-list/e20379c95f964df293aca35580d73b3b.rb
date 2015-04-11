class Deque

  def push data
    @last = build data
  end

  def pop
    @last.unlink
    data, @last = @last.data, @last.succ
    data
  end

  def unshift data
    @first = build data
  end

  def shift
    @first.unlink
    data, @first = @first.data, @first.pred
    data
  end

  private

  def build data
    elem = Element.new(data, @first, @last)
    @first ||= elem
    @last ||= elem
    @last.pred, @first.succ = [elem] * 2
    elem
  end

  private

  class Element < Struct.new(:data, :pred, :succ)
    def unlink
      succ.pred = succ
      pred.succ = pred
    end
  end

end
