class Array
  def accumulate &block
    if block_given?
      self.reduce([]) do |r, e|
        r << block.(e)
      end
    end
  end
end
