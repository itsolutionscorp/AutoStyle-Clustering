class Hamming

  def self.compute strand1, strand2
    distance = 0

    strand1_array = strand1.split('')
    strand2_array = strand2.split('')

    strand1_array.each_with_index do |point1, index|
      point2 = strand2_array[index]

      distance+=1 if point_mutation?(point1, point2)
    end

    distance
  end

  def self.point_mutation? pointa, pointb
    pointa != pointb && !pointa.nil? && !pointb.nil?
  end
  private_class_method :point_mutation?

end
