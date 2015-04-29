class Hamming
  def self.compute(first_strand, second_strand)
    hamming = 0
    first_strand_array = first_strand.split('')
    second_strand_array = second_strand.split('')
    if first_strand_array.count <= second_strand_array.count
      first_strand_array.each_index do |num|
        if first_strand_array[num] != second_strand_array[num]
          hamming += 1
        end
      end
    end
    hamming
  end
end
