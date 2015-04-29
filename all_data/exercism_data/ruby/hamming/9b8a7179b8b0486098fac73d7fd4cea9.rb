class Hamming

  def self.compute(strand1, strand2)

     total_difference = 0

     a = [strand1.length, strand2.length].min

     0.upto(a - 1) do |index_position|

       total_difference += 1 unless strand1[index_position] == strand2[index_position]

     end

     total_difference

  end

end
