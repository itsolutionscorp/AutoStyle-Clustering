module Hamming
    def self.compute(first, second)
        count = 0
        first.chars.each_with_index { |char, index|
            # ignore extra characters if our string
            # is longer than the second one.
            next unless second[index]
    
            count += 1 if char != second[index]
        }
        return count
    end
end
