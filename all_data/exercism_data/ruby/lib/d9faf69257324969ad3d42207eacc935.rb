class Array

  def keep(&block)
    self.collect do |elem|
      elem if block.call(elem)
    end
  end

  def discard(&block)
    self.collect do |elem|
      elem unless block.call(elem)
    end
  end

end
