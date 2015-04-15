class Hamming
  def self.compute(first, second)
    total_difference = 0
    first = make_first_dna_strand_no_longer_than_second(first, second)
    string_to_array(first).each_with_index do |key, value|
      total_difference += 1 if key != string_to_array(second)[value]
    end
    total_difference
  end

  def self.make_first_dna_strand_no_longer_than_second(first, second)
    return first[0, second.length]
  end

  def self.string_to_array(string)
    return string.split(//)
  end
end
