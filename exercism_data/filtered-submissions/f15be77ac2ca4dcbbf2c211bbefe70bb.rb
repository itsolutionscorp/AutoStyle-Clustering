def compute(first_strand, second_strand)
    strands = [first_strand, second_strand].sort { |a, b| a.length <=> b.length }

    count = 0
    i = 0

    strands[0].each_char do |c|

      count += 1 if strands[1][i] != c
      i += 1

    end

    count
  end