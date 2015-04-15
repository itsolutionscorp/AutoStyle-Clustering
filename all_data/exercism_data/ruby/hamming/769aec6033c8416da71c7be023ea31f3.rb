class Hamming
  def self.compute(strand1, strand2)
    strand1_parts = strand1.split("")
    strand2_parts = strand2.split("")
    
    shortest_length = strand1_parts.length < strand2_parts.length ? strand1_parts.length : strand2_parts.length
    strand1_parts = strand1_parts.slice(0, shortest_length)
    strand2_parts = strand2_parts.slice(0, shortest_length)
    
    count = 0
    strand1_parts.each_with_index do |part_one, i|
      count += 1 if strand1_parts[i] != strand2_parts[i]
    end
    
    count
  end
end
