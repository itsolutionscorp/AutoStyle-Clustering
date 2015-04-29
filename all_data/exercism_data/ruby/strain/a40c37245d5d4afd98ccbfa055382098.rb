class Array
  def keep
    new = []
    self.each_with_index do |v,i|
      new << self[i] if yield(v)
    end
    new
  end

  def discard
    new = []
    self.each_with_index do |v,i|
      new << self[i] unless yield(v)
    end
    new
  end
end
