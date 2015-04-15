class Hamming
  def self.compute str1, str2
    strand1 = Strand.new(str1)      
    strand2 = Strand.new(str2)
    distance = strand1.distance(strand2)
    puts "distance for #{str1} and #{str2} = #{distance}"
    distance
  end
end

class Strand

  attr_accessor :array
  def initialize str
    self.array = convert_to_array str
  end

  def convert_to_array str
    str.chars
  end
  
  def distance other_strand
    other_array = other_strand.array
    distance = 0
    self.array.each_index do | pos |
      distance += 1 if diff?(self.array[pos],other_array[pos])
    end
    distance
  end

  def diff? char1,char2
    char1 != char2
  end
end
