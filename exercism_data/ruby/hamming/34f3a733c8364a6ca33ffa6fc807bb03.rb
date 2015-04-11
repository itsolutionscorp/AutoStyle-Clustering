class Hamming
  def self.compute(strand1, strand2)
  	diff_count = 0
  	max_length = [strand1.length, strand2.length].min

  	i = 0
  	until i >= max_length do
  		diff_count += 1 unless strand1[i] == strand2[i]
  		i += 1
  	end

  	return diff_count
  end
end

puts Hamming.compute('AAA', 'AAB')
