class Hamming
  def self.compute arg1, arg2
    total_distance = 0
    if arg2.length < arg1.length
      arg1, arg2 = arg2, arg1
    end
    arg1.chars.each_with_index do |arg1_letter, i|
      arg2_letter = arg2[i]
      total_distance += distance(arg1_letter, arg2_letter)
    end
    total_distance
  end

  def self.distance(arg1, arg2)
    arg1 == arg2 ? 0 : 1
  end
end
