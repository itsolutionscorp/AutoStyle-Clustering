require 'pry'

class Hamming
  def self.compute(a, b)
    hamming_length = 0
    a.length.times do |i|
      hamming_length += 1 unless a[i] == b[i]
    end

    hamming_length
  end
end