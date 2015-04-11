class Array
  def accumulate
    arry = []
    self.each {|e| arry << yield(e) }
    arry
  end
end
