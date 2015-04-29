class Array
  def keep
    self.clone.each { |e| delete(e) unless yield e }
    self
  end

  def discard
    self.clone.each { |e| self.delete(e) if yield e }
    self
  end
end
