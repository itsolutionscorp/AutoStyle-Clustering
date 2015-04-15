class Array
  def accumulate(&block)
    ans = []
    for i in self
      ans << block.call(i)
    end
    ans
  end
end
