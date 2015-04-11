class Hamming
  def self.compute(str_a, str_b)
    (0...[str_a, str_b].map(&:size).min).count { |i| str_a[i] != str_b[i] }
  end
end
