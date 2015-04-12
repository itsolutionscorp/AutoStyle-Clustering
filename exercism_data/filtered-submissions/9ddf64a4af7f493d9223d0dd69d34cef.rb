class Hamming

  def compute(strand1, strand2)

    array_1 = strand1.split(//)

    array_2 = strand2.split(//)

    array_3 = []  

    if strand1 == strand2

     0

   elsif strand1 != strand2

     array_1.each_with_index do |item1, i|

      item2 = array_2[i]

      if item1 != item2

        array_3 << item1
      end

    end

    array_3.length

    end



    end
  end
