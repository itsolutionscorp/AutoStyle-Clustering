# V2 - code changed due to good practices described within https://github.com/styleguide/ruby
class Hamming
  def compute(a,b)

# a,b are strings; transformation to array, checking size

    array_a = a.each_char.to_a
    size_a = array_a.size
    array_b = b.each_char.to_a
    size_b = array_b.size

# calculating min size of two arrays to compare proper part of input values;
# if/then/else block converted to ternary operator

   min = (size_a > size_b) ? size_b : size_a

# min=min-1 because array items starts from 0; "-=" is working
# hamming as searched value; i as a counter for "cut to min size" arrays comparison

    min -= 1
    hamming = 0
    i = 0

#comparing values in two arrays limited to "min" length
# if/then/else block converted to ternary operator

    until i > min do
      (array_a[i].eql?(array_b[i])) ? i += 1 : (hamming += 1; i += 1)
    end
    hamming
  end
end
