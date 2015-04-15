class Array

  def keep(&block)
    result = []
    each do |element|
      result << element if yield element
    end
    result
  end

  def discard(&block)
    result = []
    each do |element|
      result << element unless yield element
    end
    result
  end

end
