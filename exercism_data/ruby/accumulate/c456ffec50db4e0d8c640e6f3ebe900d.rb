class Array
  def accumulate
    collect = []
    self.each do |n|
      collect << yield(n)
    end
    collect
  end
end
