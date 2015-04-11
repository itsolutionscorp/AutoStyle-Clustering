class Array

  def keep
    raise ArgumentError.exception('no block') unless block_given?
    self.select { |e| e if yield e }

  end

  def discard
    raise ArgumentError.exception('no block') unless block_given?
    self.select { |e| e unless yield e }
  end

end
