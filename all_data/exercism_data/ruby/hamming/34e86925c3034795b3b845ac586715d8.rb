class Hamming

  def self.compute(first, second)
    compairs = create_compairs_from(first, second)
    count_differences_in(compairs)
  end

  private
  def self.count_differences_in(compairs)
    compairs.count { |elements| elements[0] != elements[1] }
  end

  def self.create_compairs_from(first, second)
    compairs = first.chars.zip(second.chars)
    compairs.reject { |elements| elements.include?(nil) }
  end

end
