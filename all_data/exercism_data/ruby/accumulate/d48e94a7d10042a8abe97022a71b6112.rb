class Array

  def accumulate(&block)
    output = []
    self.each { |object| output << (block.call object) }
    output
  end

end
