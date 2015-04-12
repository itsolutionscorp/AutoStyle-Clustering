class Hamming
  def compute(first_strand, second_strand)
    strands = [first_strand, second_strand].sort { |a, b| a.length <=> b.length }

    count = 0

    strands[0].length.times do |i|

      count += 1 if strands[1][i] != strands[0][i]

    end

    count
  end
end
