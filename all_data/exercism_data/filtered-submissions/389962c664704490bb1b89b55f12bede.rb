class Hamming 

      def Hamming.compute strandA, strandB 
        hamming_distance = 0
        end_point = strandA.length
        length_difference = strandA.length - strandB.length

        if length_difference > 0
            end_point = strandB.length
        end

        end_point -= 1;

        for i in ( 0..end_point )
               if strandA[i] != strandB[i]
                    hamming_distance += 1
               end
        end

        return hamming_distance

    end

end
