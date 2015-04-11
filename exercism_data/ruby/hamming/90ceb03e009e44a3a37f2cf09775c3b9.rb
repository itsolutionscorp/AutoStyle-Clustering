class Hamming

  def self.compute first_string, second_string
   difference = 0
   if first_string.length == second_string.length
     first_string.chars.each_with_index do |char, index|
       difference += 1 unless char == second_string[index]
     end
   end
   difference
  end

end
