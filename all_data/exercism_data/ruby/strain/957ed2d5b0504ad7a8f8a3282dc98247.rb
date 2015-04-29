class Array
  def keep
    to_keep = []

    self.each do |item|
      to_keep << item if yield(item)
    end

    to_keep
  end

  def discard
   to_keep = []

    self.each do |item|
      to_keep << item unless yield(item)
    end

    to_keep
  end
end
