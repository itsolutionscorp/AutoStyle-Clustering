class Hamming

  def compute(strand1, strand2)

     total_difference = 0

     a = [strand1.length, strand2.length].min

     a.times do |index_position|

       total_difference += 1 if strand1[index_position] != strand2[index_position]

     end

     total_difference

  end

end
