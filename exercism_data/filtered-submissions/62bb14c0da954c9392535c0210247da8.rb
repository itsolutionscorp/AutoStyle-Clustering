class Hamming
  def compute(first_string, second_string)
    first_string.chars.zip(second_string.chars).count { |nuc_a, nuc_b| nuc_a != nuc_b }
  end
end
