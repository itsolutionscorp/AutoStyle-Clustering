class Array
  def accumulate
    map { |element| yield element }
  end
end
