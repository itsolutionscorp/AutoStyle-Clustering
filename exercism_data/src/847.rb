def compute mine, yours
    min_length = [mine.length, yours.length].min

    # truncate input
    mine = mine[0, min_length]
    yours = yours[0, min_length]

    distance = mine.chars.each_with_index.reduce(0) do |memo, (char, index)|
      if yours[index] != char
        memo += 1
      end

      memo
    end
  end