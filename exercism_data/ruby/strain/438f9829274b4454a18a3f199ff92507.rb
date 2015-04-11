class Array
  def keep(&block)
    ans = []
    self.each do |x|
      ans << x if block.call(x)
    end
    ans
  end

  def discard(&block)
    ans = []
    self.each do |x|
      ans << x unless block.call(x)
    end
    ans
  end
end
