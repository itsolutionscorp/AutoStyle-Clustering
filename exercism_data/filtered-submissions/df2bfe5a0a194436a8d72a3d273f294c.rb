class Hamming

  def compute(strand_1, strand_2)
    place_holder = 0
    differences = 0

    strand_1.each_char do |c|
      if c != strand_2[place_holder]
        differences += 1
      end
      place_holder += 1
      break if place_holder >= strand_1.length || place_holder >= strand_2.length
    end

    return differences
  end
end
