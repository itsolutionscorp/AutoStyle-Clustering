class Array
  def keep
    res = []

    non_enumerable_each do |item|
      res << item if yield item
    end

    res
  end

  def discard
    keep{ |item| !yield(item) }
  end

  private

  def non_enumerable_each
    i = 0
    while i < length
      yield slice(i)
      i += 1
    end
  end
end
