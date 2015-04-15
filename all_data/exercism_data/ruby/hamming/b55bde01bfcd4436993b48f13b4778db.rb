class Hamming
  def self.compute(a,b)
    mutant_count = 0
    a.split(//).each_with_index do |char, index|
      mutant_count +=1 if index < b.length and char != b[index]
    end
    mutant_count
  end
end
