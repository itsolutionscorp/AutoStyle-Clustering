class Element
  FLIP = -> (f) { -> (x, y) { f[y, x] }}
  CONS = -> (x, y) { new(x, y) }

  class << self
    def to_a(arg)
      Array(arg)
    end

    def from_a(ary)
      head, *tail = Array(ary)
      return Null.new unless head
      CONS[head, from_a(tail)]
    end
  end

  attr_reader :datum, :_next
  alias_method :next, :_next

  def initialize(datum, _next = nil)
    @datum, @_next = datum, (_next || Null.new)
  end

  def to_a
    foldl :push.to_proc, []
  end

  def reverse
    foldl FLIP[CONS], Null.new
  end

  def length # not demanded by tests
    foldl -> (acc, _) { acc + 1 }, 0
  end

  def map(&func) # not demanded by tests
    foldr -> (x,acc) { CONS[func[x], acc] }, Null.new
  end

  def select(&func) # not demanded by tests
    foldr -> (x,acc) { func[x] ? CONS[x, acc] : acc }, Null.new
  end

  def foldl(func, init)
    _next.foldl(func, func[init, datum])
  end

  def foldr(func, init)
    func[datum, _next.foldr(func, init)]
  end
end

class Null < Element
  def initialize(*);       end
  def nil?;           true end
  def foldl(_, init); init end
  def foldr(_, init); init end
end
