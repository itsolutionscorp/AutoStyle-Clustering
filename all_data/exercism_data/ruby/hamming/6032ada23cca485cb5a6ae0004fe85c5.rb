class Hamming
  def self.compute(first, second)
    return nil if first.length != second.length
    first.chars.zip(second.chars).reduce(0) do |distance, column|
      column[0] == column[1] ? distance : distance + 1
    end
  end
end
