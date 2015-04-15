class Hamming
  def self.compute(first_string,second_string)
    first_string.chars.zip(second_string.chars).count { |pair| pair[0] != pair[1] }
  end
end
