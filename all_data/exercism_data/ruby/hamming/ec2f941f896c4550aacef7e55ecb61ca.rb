class Hamming
  def self.compute(strand1, strand2)
    min = min_length(strand1.length, strand2.length)
    match_array = [*0..min].map { |i| strand1.split('')[i] == strand2.split('')[i] }
    number_of_differences(match_array)
  end

  def self.min_length(strand1_length, strand2_length)
    (strand1_length > strand2_length ? strand2_length : strand1_length) - 1 
  end

  def self.number_of_differences(match_array)
    match_array.count { |i| i == false }
  end
end
