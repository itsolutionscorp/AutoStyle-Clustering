class Array
  def accumulate
    data = []
    each do |input|
      data << yield(input)
    end
    data
  end
end
