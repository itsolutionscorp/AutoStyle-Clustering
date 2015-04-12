class Hamming
  
  def compute(strand_one, strand_two)
    hamming_distance = strand_one.chars.map.with_index {|char, index| char != strand_two[index] }.count(true)
  end                                                                                                                # => :compute
end                                                                                                                  # => :compute
