class Hamming
  def compute(strand_a, strand_b)

    hamming = 0
    strand1 = strand_a.chars
    strand2 = strand_b.chars

    strand1.zip(strand2).each do |strands_array|
      hamming += 1 if strands_array.first != strands_array.last
    end
    hamming
  end
end

#break into elements by character
#assemble into array
#check for differences between elements at each position in each array
#look at the same index in each array
