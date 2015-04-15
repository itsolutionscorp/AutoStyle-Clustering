def compute(first, second)
        count = 0
        first.chars.each_with_index { |char, index|


            next unless second[index]

            count += 1 if char != second[index]
        }
        return count
    end