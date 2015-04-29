class Hamming

  def self.compute(first, second)
    compairs = create_compairs_from(first, second)
    count_differences_in(compairs)
  end

  private
  def self.count_differences_in(compairs)
    compairs.inject(0) { |result, elements| elements[0] == elements[1] ? result : result + 1 }
  end

  def self.create_compairs_from(first, second)
    tokenize(first).zip(tokenize(second)).reject { |elements| elements.include?(nil) }
  end

  def self.tokenize(string)
    string.split(//)
  end
end
