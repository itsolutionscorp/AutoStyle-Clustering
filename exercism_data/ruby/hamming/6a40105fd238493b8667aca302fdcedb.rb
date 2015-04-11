class Hamming

  def self.compute(a, b)

    # establish differences counter, starting at 0
    diff ||= 0

    # put x and y into arrays
    first = a.split("")
    second = b.split("")

    # count array, assuming that x and y are identical in length
    length = first.length

    # compare each item in array, adding 1 to the counter if they are different
    length.times do |x|
      unless first[x] == second[x]
        diff += 1    
      end
    end

    # return diff
    diff

  end
 
end
