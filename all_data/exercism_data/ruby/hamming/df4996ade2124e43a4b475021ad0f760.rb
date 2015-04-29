require 'pp'

class Hamming

  def self.strand_one(string)
    string.upcase.split(//)
  end

  def self.strand_two(string)
    string.upcase.split(//)
  end

  def self.compute(string1,string2)
    new_array = []

    strand_one(string1).each_with_index {|x, in1| strand_two(string2).each_with_index {|y, in2| if x != y && in1 == in2 then     new_array << x end }  }

     new_array.count
   end
end
    
