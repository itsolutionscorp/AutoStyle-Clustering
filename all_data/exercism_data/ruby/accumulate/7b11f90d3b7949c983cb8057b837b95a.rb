class Array
  def accumulate(&block)
    new_self = self.class.new
    each do |item|
      new_self << yield(item)
    end
    new_self
  end
end
