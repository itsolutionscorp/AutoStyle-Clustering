class Array
  def keep
    bin = []
    for i in self
      bin << i if yield i
    end
    bin
  end

  def discard
    bin = []
    for i in self
      bin << i if !(yield i)
    end
    bin
  end
end
