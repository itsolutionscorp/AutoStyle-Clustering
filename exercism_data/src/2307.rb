#Class compares two strings and returns the number of same-position characters that are different between them.
class HammingCompare
  def compute(a, b)
    #No need to calculate if they are equal
    if a === b
      return 0
    end
    #find minimum length of the strings
    length = a.length < b.length ? a.length : b.length

    #initialize variable to count differences between strings
    difference = 0

    #check each character, up to the length of the smaller string against the same position character of the other string and count how many are different
    for i in 0..(length-1) do
      if a[i] != b[i]
        difference = difference + 1
      end
    end
    return difference
  end
end

#instantiate class
Hamming = HammingCompare.new
