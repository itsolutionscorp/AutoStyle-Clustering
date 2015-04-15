class Array
  def keep &block
    self.map do |n|
      n if block.call(n)
    end.compact
  end

  def discard &block
    self.map do |n|
      n unless block.call(n)
    end.compact
  end
end
