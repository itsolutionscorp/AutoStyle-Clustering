class Hamming
  def self.compute(a, b)
    distance = 0
    a_strand = Strand.new(a.chars)
    b_strand = Strand.new(b.chars)

    (0..(a_strand.length)).each do |i|
      unless b_strand.at(i).nil? || a_strand.at(i).nil?
        (distance += 1) if a_strand.mutation?(i, b_strand)
      end
    end

    distance
  end
end

class Strand < Array
  def mutation?(position, foreign_strand)
    at(position) != foreign_strand.at(position)
  end
end
