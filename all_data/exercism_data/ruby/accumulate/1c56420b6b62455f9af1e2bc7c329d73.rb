module Enumerable

  def accumulate
    a = []
    if block_given?
      each { |i| a << yield(i) }
      return a
    else
      return self
    end
  end

end
