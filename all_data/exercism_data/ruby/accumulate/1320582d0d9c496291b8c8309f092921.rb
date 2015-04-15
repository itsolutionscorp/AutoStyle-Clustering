class Object
  def accumulate
    result = []
    self.each {|x| result << (yield x)}
    result
  end
end
