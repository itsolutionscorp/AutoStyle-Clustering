def compute(one, two)
    shortest_strand = [one, two].min_by { |strand| strand.length }

    distance = 0
    (0...shortest_strand.length).each do |i|
      distance += 1 if one[i] != two[i]
    end

    distance
  end