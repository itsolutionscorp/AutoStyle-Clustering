# vim:fileencoding=utf-8


class Hamming
  def self.compute(strand_a, strand_b)
    return 0 if strand_a == strand_b

    minlen = [strand_a.length, strand_b.length].min
    array_a = strand_a.scan(/./)[0,minlen]
    array_b = strand_b.scan(/./)[0,minlen]

    array_a.zip(array_b).count { |a, b| a != b }
  end
end
