class Hamming
  
  class << self
    def compute strand1, strand2
      distance = 0

      strand1_array = strand1.split('')
      strand2_array = strand2.split('')

      strand1_array.each_with_index do |point1, index|
        point2 = strand2_array[index]

        if point_mutation?(point1, point2)
          distance+=1
        end
      end

      distance
    end

    private

    def point_mutation? pointa, pointb
      pointa != pointb && !pointa.nil? && !pointb.nil?
    end

  end

end
