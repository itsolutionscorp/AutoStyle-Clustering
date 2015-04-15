class Hamming
  def self.compute(strand0, strand1)
    count_diffs strand0.chars.zip(strand1.chars)
  end

  def self.count_diffs(array)
    array.each.count { |elem| elem[1] != nil && elem[0] != elem[1] }
  end
end
