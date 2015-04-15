class Array
  def keep(&block)
    [].tap do |out|
      self.each { |e| out << e if block.call(e) }
    end
  end

  def discard(&block)
    [].tap do |out|
      self.each { |e| out << e unless block.call(e) }
    end
  end
end
