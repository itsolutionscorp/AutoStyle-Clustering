class Array
  #alias :keep :keep_if
  #alias :discard :delete_if

  def keep
    self.map { |e| e if yield e}.compact
  end

  def discard
    self.map { |e| e unless yield e}.compact
  end
end
