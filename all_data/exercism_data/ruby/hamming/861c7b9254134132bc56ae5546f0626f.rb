class Hamming
  def self.compute(sequence_a, sequence_b)
    sequence_a.chars.zip(sequence_b.chars). # zip the character sequences together
      delete_if { |a, b| a.nil? || b.nil? }. # ignore extra length
      map { |a, b| a == b ? 0 : 1 }. # map matching characters to 0, non-matching to 1
      reduce(:+) # calculate the sum, which will be the hamming distance
  end
end
