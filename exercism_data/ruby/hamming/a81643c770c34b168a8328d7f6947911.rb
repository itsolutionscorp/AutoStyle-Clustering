class Hamming

  def self.compute(strand_a, strand_b)
    array_a, array_b = to_array(strand_a), to_array(strand_b)
    list_of_booleans = array_a.zip(array_b).map { |a,b| a == b }
    count_false(list_of_booleans)
  end

  def self.to_array(string)
    string.split(//)
  end

  def self.count_false(list)
    counts = Hash.new 0
    list.each do |word|
      counts[word] += 1
    end
    counts[false]
  end

end
