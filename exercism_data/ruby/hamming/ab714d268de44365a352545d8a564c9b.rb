# require 'minitest/autorun'
# require_relative 'hamming'


class Hamming

  def self.compute(a, b)
    index = 0
    hamming_dist = 0

    if a.length > b.length
      while index < a.length
        if a[index] != b[index] && b[index] != nil
          hamming_dist += 1
        end
        index += 1
      end
    elsif a.length < b.length
      while index < b.length
        if a[index] != b[index] && a[index] != nil
          hamming_dist += 1
        end
        index += 1
      end
    end

    hamming_dist
  end

end
