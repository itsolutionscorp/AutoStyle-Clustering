class Hamming
  def compute(first_strand, second_strand)

    short_strand, long_strand = [first_strand, second_strand].sort { |a, b| a.length <=> b.length }
    count = 0

    short_strand.length.times do |i|

      count += 1 if long_strand[i] != short_strand[i]

    end

    count
  end
end
