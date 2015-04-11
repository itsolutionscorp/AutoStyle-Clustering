class Hamming
  def self.compute(a, b)
    return 0 if a.length.zero? or b.length.zero?
    smallest_strand = a.length > b.length ? b : a
    smallest_strand.length - a.each_char.with_index.count { |x, i| x.eql? b[i] }
  end
end
