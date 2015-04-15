class Array
  def accumulate
    result = []
    # Or, replace each with:
    # for element in self
    each do |element|
      result.push(yield element)
    end
    result
  end
end
