class Array
  def accumulate
    returnarr = []
    self.each do |x|
      returnarr << yield(x)
    end
    returnarr
  end
end
