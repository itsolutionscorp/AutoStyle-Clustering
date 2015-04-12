def compute(first_str, second_str)
    min_length = [first_str.length, second_str.length].min

    hamms = 0
    min_length.times do |i|
      hamms += 1 if first_str[i] != second_str[i]
    end
    hamms
  end