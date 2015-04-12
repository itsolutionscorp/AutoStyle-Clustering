require 'byebug'

class Hamming
  def compute(a, b)
    @hamming = 0
    a.chars.each_with_index do |value, index|
      if value != b.chars[index]
        @hamming += 1
      end
    end
    return @hamming
  end
end
