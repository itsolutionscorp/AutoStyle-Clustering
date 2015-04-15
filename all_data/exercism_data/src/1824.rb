def compute(first_string, second_string)
    
    differences = 0

    first_string.chars.zip(second_string.chars).each do |one, two|
      if !two.nil? && one != two
        differences += 1
      end
    end

    return differences
  end