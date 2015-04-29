class Hamming

  def self.compute(strand1, strand2)
     diff_catcher = 0

     a = [strand1.length, strand2.length].min

     0.upto(a - 1) do |index_position|

       if strand1[index_position] == strand2[index_position]
         diff_catcher += 0
       else
         diff_catcher += 1
       end

     end

     diff_catcher

  end

end
