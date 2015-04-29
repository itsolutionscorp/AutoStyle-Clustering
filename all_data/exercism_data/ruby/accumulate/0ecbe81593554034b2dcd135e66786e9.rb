class Array
  def accumulate
    out = []
    each{|i|
      out << yield(i)
    }
    out
  end
end
