def compute(first, second)
    first_array = first.chars
    second_array = second.chars

    counter = 0

    first_array.each_with_index do |letter, count|
      counter += 1 if letter != second_array[count]
    end
    counter
  end