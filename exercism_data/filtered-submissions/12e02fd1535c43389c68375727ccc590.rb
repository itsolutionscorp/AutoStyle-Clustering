class Hamming
  def compute strand_one, strand_two
    
    return if strand_one.nil? || strand_two.nil?
    difference = 0

    0.upto([strand_one.size, strand_two.size].min - 1) do |i|
      difference += 1 if strand_one[i] != strand_two[i]
    end

    difference
  end
end
