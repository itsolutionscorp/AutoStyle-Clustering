module KeepAndDiscard

  def keep
    each_with_object [] do |element, result|
      result << element if yield element
    end
  end

  def discard
    each_with_object [] do |element, result|
      result << element unless yield element
    end
  end

end

Array.send :include, KeepAndDiscard
