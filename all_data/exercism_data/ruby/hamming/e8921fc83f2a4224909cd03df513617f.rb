class Hamming
  def self.compute(first_input, second_input)
    first_input.chars.zip(second_input.chars).count { |(first,last)| first != last unless last == nil }
  end
end
