require 'prime'

class Raindrops

  def self.convert(integer)
    array1 = integer.prime_division
    array_size = array1.size
    string1=''
    (0..array_size-1).each do |i|

      case array1[i][0]
        when 3
          string1 << "Pling"
        when 5
          string1 << "Plang"
        when 7
          string1 << "Plong"
      end
    end
    if string1.size == 0
      return integer.to_s
    else
      string1
    end
  end
end
