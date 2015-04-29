class Array
  def keep
    return [] if self == []

    self.each_with_object([]) do |e, m|
      m << e if yield e
    end
  end

  def discard
    return [] if self == []

    self.each_with_object([]) do |e, m|
      m << e unless yield e
    end
  end
end
