class Array

  def keep(&block)
    result = []
   (self.empty?) ? [] : self.each{|i| result << i if yield(i)}
    result
  end

  def discard(&block)
    result = []
    (self.empty?) ? [] : self.each{|i| result << i unless yield(i)}
    result
  end

end
