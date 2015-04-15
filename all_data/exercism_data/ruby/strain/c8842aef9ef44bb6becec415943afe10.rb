class Array
  def keep &condition
    keep = []
    self.each do |num|
      keep.push(num) if condition.call(num)
    end
    keep
  end
  
  def discard &condition
    discard = []
    self.each do |num|
      discard.push(num) unless condition.call(num)
    end
    discard
  end
end
