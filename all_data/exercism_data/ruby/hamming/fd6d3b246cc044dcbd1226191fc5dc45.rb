class Hamming
  class << self # all methods are class methods in this exercise for some reason
    def compute strand1, strand2
      calculate_distance_for each_base_pair_of strand1, strand2
    end

    private

    def each_base_pair_of strand1, strand2
      strand1, strand2 = strand1.split(''), strand2.split('')
      strand1.zip strand2
    end

    def calculate_distance_for pairs
      pairs.reduce 0 do |count, pair|
        return count unless pair[0] && pair[1] # if one is shorter, ignore extras
        if pair[0] == pair[1]
          count
        else
          count + 1
        end
      end
    end
  end
end
