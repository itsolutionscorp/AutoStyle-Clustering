class Array

  def keep
    results = map do |x|
      x if yield x
    end
    results.compact
  end

  def discard
    results = map do |x|
      x if (yield x) == false
    end
    results.compact
  end
end
