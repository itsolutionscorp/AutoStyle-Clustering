class Array
  def keep
    each_with_object([]) do |element, selected|
      selected << element if yield element
    end
  end

  def discard
    each_with_object([]) do |element, selected|
      selected << element unless yield element
    end
  end
end
