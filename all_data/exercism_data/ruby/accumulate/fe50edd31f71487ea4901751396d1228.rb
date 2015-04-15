class Array
  def accumulate
    r = []
    self.each do |item|
      r.push( yield item )
    end
    r
  end
end
