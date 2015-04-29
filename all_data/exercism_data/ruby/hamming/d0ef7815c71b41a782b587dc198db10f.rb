class Hamming
  def self.compute(base, test)
    max_length_to_compare = [base.size,test.size].min
    max_length_to_compare.times.count { |i| base[i] != test[i] } 
  end
end
