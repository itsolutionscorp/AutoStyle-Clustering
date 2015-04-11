class Array
  def keep
    map { |element| element if yield element }.compact
  end
  
  def discard
    map { |element| element unless yield element }.compact
  end
end
