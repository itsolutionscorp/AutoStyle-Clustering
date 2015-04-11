# Write a program that can calculate the Hamming difference between two DNA strands.
class Hamming
  def self.compute(a, b)
    new(a, b).compute
  end

  def initialize(a, b)
    @a, @b = a, b
  end

  def compute
    0.upto(length - 1).inject(0) do |memo, idx|
      memo += 1 if @a[idx] != @b[idx]

      memo
    end
  end

private

  def length
    [@a.length, @b.length].min
  end

end
