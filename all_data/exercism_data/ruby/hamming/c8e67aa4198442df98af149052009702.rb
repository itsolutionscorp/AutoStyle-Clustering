#!/usr/bin/ruby

class Hamming
  def self.compute(onez,twoz)

    arr1 = onez.scan /\w/
    arr2 = twoz.scan /\w/

    # are the two arrays the same length
    if arr1.size == arr2.size
      z = 0
      arr1.each_with_index\
        {|str,iterator|
        z += 1 if arr1[iterator] != arr2[iterator]
      }
      z
      exit 0
    else
      exit 1
    end
  end
end
