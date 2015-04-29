class Array

  def accumulate
    arr = []
    self.each {|x| arr << (yield x)}
    arr
  end
  
end
