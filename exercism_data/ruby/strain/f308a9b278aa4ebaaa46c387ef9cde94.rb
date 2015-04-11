class Array
	def keep(&block)
    self.map do |i|
      i if yield i
    end.compact
  end

  def discard(&block)
    self - self.keep(&block)
  end
end
