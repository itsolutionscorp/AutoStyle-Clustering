def compute (strand_one, strand_two)
    min_distance = nil
    one, two = [strand_one, strand_two].sort { |a, b| a.length <=> b.length }

    0.upto(two.length - one.length) do |offset|
      distance = 0
      0.upto(one.length - 1) do |index|
        if one[index] != two[index + offset]
          distance += 1
        end
      end
      min_distance = min_distance.nil? ? distance : [distance, min_distance].min
    end

    return min_distance
  end