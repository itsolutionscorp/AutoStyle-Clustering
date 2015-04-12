require 'pry'
class Hamming
  def compute(a, b)
    shortest = [a.length, b.length].sort.first
    a_strand = a.chars[0..shortest-1]
    b_strand = b.chars[0..shortest-1]

    diff = 0
    a_strand.map.with_index do |str, i|
      diff += 1 unless b_strand[i] == str
    end
    diff
  end
end
