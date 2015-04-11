require 'awesome_print'

class Hamming

  def self.compute(a, b)
    return 0 if a == b # performance!
    
    a, b = uniform_input a, b

    diffs = 0
    (0..a.length).each do |i|
      diffs += 1 if a[i] != b[i]
    end
    diffs
  end

  private

    # Trim excess of code from either input:
    #   AAA, BBBBB -> AAA, BBB
    #   AAAAA, BBB -> AAA, BBB
    def self.uniform_input(a, b)
      max_length = [a.length, b.length].min
      [a[0..max_length-1], b[0..max_length-1]]
    end

end
