class Hamming
  def self.compute(a, b)
    a.bytes.keep_if { |c| !b.empty? && c != b.slice!(0).ord }.size
  end
end
