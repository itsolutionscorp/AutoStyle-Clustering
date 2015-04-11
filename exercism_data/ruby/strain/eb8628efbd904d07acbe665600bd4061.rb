class Array

  def keep
    result = []
    each do |x|
      if yield x
        result << x
      end
    end
    result
  end

  def discard
    result = []
    each do |x|
      if !yield x
        result << x
      end
    end
    result
  end
end
