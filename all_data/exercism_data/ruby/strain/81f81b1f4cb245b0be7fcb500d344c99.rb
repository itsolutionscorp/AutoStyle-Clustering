class Array
  def keep
    out = []
    each {|i| out << i if yield(i)}
    out
  end

  def discard
    out = []
    each {|i| out << i if not yield(i)}
    out
  end
end
