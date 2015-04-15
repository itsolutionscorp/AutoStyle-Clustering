class Hamming

  def self.compute(arg1, arg2)
    if arg1.length != arg2.length
      abort (message='Sequences must be of equal length')
    else
      arg1 = arg1.chars
      arg2 = arg2.chars
      distance = 0
      arg1.each_index do |index|
        distance = distance+1 if not arg1[index] == arg2[index]
        end
    end
    return distance
  end
end
