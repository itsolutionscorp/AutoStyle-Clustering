class BinarySearch
  def initialize(l)
    raise ArgumentError if l != l.sort
    @l = l
  end

  def list
    @l
  end

  def search_for(value)
    raise RuntimeError unless @l.include?(value)
    s = 0
    e = @l.size - 1
    loop do
      m = (s + e) / 2
      if @l[m] < value
        s = m
      elsif @l[m] > value
        e = m
      else
        return m
      end
    end
  end

  def middle
    (@l.size) / 2
  end
end
