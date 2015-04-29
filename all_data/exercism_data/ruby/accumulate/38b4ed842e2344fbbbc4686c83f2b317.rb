class Array
  def accumulate
    out = []
    each { |e| out << yield(e) }
    out
  end
end
