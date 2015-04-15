class Hamming

  def self.compute(first, second)
    first_array = first.split('')
    second_array = second.split('')
    hamming = 0
    if first_array.count <= second_array.count
      first_array.count.times do |num|
        if first_array[num] != second_array[num] && !first_array[num].empty? && !second_array[num].empty?
          hamming += 1
        end
      end
    end
    hamming
  end
end
