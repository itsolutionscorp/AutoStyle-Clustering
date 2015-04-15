class Array
  def accumulate
    res = self.class.new
    each { |e| res << (yield e) }
    res
  end
end
