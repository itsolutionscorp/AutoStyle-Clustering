#
class Hamming
  def self.convert_lengths(val1, val2)
    val1 = val1.take(val2.size) if val1.size > val2.size
    val2 = val2.take(val1.size) if val2.size > val1.size
    [val1, val2]
  end

  def self.same_lengths(val1, val2)
    val1.zip(val2).select do |v|
      v.uniq.size != 1
    end.size
  end

  def self.compute(a, b)
    val1, val2 = [a, b].map { |v| v.scan(/\w/) }
    val1, val2 = convert_lengths(val1, val2)
    same_lengths(val1, val2)
  end
end
