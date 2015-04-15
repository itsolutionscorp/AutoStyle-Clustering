class Array
  def keep
    res = self.class.new
    each { |e| res << e if yield(e) }
    res
  end

  def discard
    res = self.class.new
    each { |e| res << e unless yield(e) }
    res
  end
end
