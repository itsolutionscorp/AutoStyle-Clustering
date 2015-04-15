class Array
  def keep &block
    res = []
    self.each { |d|
      if block[d] then
        res << d
      end
    }
    res
  end

  def discard &block
    res = []
    self.each { |d|
      if not block[d] then
        res << d
      end
    }
    res
  end
end
