def compute(first, second)
    distance = 0

    first.each_char.with_index do |c, i|
      distance += 1 if c != second[i]
    end

    distance




  end