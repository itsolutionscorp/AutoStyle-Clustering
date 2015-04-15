class Hamming
  def self.compute(first, second)
    first.chars.zip(second.chars).count do |first, second|
      first != second
    end
  end
end
