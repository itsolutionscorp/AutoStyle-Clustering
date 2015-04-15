class Array

  def keep
    kept = []
    self.each do |object|
      kept << object if yield object
    end

    kept
  end

  def discard
    kept = []
    self.each do |object|
      kept << object if !yield object
    end

    kept
  end

end
