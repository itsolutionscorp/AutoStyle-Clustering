class Hamming
  def self.compute(str_a, str_b)
    mismatches = 0
    comparison_length = str_a.size < str_b.size ? str_a.size : str_b.size
    comparison_length.times do |n|
      mismatches += 1 if str_a[n] != str_b[n]
    end

    mismatches
  end
end
