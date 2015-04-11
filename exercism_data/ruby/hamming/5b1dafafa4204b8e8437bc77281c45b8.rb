class Hamming
  class Error < StandardError; end
  class ArgumentError < StandardError; end

  def self.compute(a, b)
    unless a.length == b.length
      raise ArgumentError, "Expected strands of the same length, got #{a} and #{b}"
    end

    a.split('').each_with_index.map { |_, i| a[i] != b[i] }.select { |n| n }.length
  end
end
