class Array
  def keep
    each_with_object([]) do |element, result|
      passed = yield element
      result << element if passed
    end
  end

  def discard
    each_with_object([]) do |element, result|
      passed = yield element
      result << element unless passed
    end
  end
end
