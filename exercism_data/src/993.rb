def compute(first_set, second_set)
    first_set.chars.each_with_index.count do |letter, index|
      letter != second_set[index]
    end
  end