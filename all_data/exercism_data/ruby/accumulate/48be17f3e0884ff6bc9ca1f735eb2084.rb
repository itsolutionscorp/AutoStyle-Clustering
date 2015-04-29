class Array
  def accumulate
    inject([]) { |a, e| a << yield(e) }
  end
end
