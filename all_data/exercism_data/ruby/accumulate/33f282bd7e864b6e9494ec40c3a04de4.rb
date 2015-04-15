class Array
  def accumulate
    output = []
    each do |element|
      output << yield(element)
    end
    output
  end
end
