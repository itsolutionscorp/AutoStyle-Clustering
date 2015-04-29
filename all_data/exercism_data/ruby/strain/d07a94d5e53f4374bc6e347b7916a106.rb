class Array
  def keep
    reduce([]) { |a, e| yield(e) ? a << e : a }
  end

  def discard
    reduce([]) { |a, e| yield(e) ? a : a << e }
  end
end
