class Array
  def accumulate
    output = []
    for i in self
      output << yield(i)
    end
    output
  end
end
