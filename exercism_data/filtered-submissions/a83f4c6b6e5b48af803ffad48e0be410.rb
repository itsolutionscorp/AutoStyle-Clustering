def compute(*strings)
    short_string, long_string = strings.sort_by(&:length)

    result = 0
    short_string.chars.each_with_index do |item, index|
      result += 1 if short_string[index] != long_string[index]
    end
    result
  end