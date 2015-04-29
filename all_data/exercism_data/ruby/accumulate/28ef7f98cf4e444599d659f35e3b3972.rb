class Array
  def accumulate &func
    self.reduce([]){|acc, x| acc << (func.call(x))}
  end
end
