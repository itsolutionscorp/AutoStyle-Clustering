class Hamming

  def self.compute(first, second)
    (0...common_length(first, second)).count do |index|
      first[index] != second[index]
    end
  end

  private

  def self.common_length(first, second)
    [first.length, second.length].min
  end

end
