require 'pry'
class Hamming
  def self.compute(a, b)
    if a.length <= b.length
      aa = a
      a = b
      b = aa
    end

    a_strand = a.split('')[0..b.length-1]
    b_strand = b.split('')

    diff = []
    a_strand.map.with_index do |str, i|
      diff << str unless b_strand[i] == str
    end
    diff.length
  end
end
