class Hamming
  
  def self.compute(first_string, second_string)
    short_string = determine_shortest_string(first_string, second_string)

    differences = 0

    short_string.chars.each_with_index do |c, i|
      unless first_string[i] == second_string[i]
        differences += 1
      end
    end

    return differences
  end

  def self.determine_shortest_string(first_string, second_string)
    if first_string.length < second_string.length
      short_string = first_string
    else
      short_string = second_string
    end

    return short_string
  end
end
