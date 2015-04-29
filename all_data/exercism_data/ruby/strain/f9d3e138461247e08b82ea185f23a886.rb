class Array
  def keep(&block)
    result = []
    each do |element|
      result.push(element) if yield element
    end
    result
  end

  def discard(&block)
    result = []
    each do |element|
      result.push(element) unless yield element
    end
    result
  end
end
