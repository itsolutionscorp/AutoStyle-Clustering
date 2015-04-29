class Array

  def accumulate( &block )
    rv = []
    self.each do |i|
      x = block.call i
      rv << x
    end
    rv
  end
end
