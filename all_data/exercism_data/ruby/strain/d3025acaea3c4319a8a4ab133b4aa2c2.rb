class Array
  def keep(&block)
    return [] if self.empty?
    element = self.shift
    return [element] + self.keep(&block) if block.call(element)
    return self.keep(&block)
  end

  def discard(&block)
    return [] if self.empty?
    element = self.shift
    return [element] + self.discard(&block) unless block.call(element)
    return self.discard(&block)
  end
end
