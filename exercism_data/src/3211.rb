def compute(a, b)
    # Start with shortest strand
    source, target = [a, b].sort_by { |c| c.length }

    distance = 0

    source.split('').each_with_index do |char, i|
      distance += 1 if char != target[i]
    end

    distance
  end