class Array

  def accumulate(&block)
    array = []
    self.each {|stuff| array << (block.call stuff)}
    array
  end
end
