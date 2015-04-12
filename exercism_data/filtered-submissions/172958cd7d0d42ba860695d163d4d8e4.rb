def compute(value1, value2)
    value1 = value1.split("")
    value2 = value2.split("")
    val1_count = value1.count
    val2_count = value2.count

    if val1_count != val2_count
      if val1_count > val2_count
        (val1_count - val2_count).times { value1.pop }
      elsif val2_count > val1_count
        (val2_count - val1_count).times { value2.pop }
      end
    end

    count = 0

    value1.each_with_index do |value, index|
      unless value1[index] == value2[index]
        count += 1
      end
    end

    return count
  end