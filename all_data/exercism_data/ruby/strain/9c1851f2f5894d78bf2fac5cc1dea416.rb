class Array
  def keep &block
    map { |element| element if yield element }.compact
  end

  def discard &block
    map { |element| element unless yield element }.compact
  end
end
