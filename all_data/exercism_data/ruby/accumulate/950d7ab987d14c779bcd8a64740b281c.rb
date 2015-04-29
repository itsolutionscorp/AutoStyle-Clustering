class Array
  def accumulate
    output = []
    each do |x|
      output << yield(x)
    end
    output
  end
end
