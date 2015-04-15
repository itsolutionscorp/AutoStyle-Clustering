module Hamming

  def self.compute base_string, comparison_string
    hamming_distance = 0
    base_array = base_string.split(//)
    base_array.each_index do |i|
      unless base_array[i].eql?(comparison_string[i]) || comparison_string[i].nil?
        hamming_distance += 1
      end
    end

    hamming_distance
  end
end
