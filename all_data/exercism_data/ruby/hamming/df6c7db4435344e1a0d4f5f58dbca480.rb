class Hamming
	def self.compute (string_a, string_b)
    return 0 if string_a == string_b

    length = shortest_length string_a,string_b
    
    error=0
    length.times do |idx|
      error += 1 if string_a[idx] != string_b[idx]
    end
    return error
	end

  def self.shortest_length string_a, string_b
    [string_a.length, string_b.length].min
  end
end
