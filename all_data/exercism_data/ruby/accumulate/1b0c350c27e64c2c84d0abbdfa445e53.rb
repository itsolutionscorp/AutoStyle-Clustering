class Array

  def accumulate(&block)
     self.map do |object|
       block.call object
     end
  end

end
