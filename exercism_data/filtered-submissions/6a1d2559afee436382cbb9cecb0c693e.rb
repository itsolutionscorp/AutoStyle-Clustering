def compute(first, second)
      return if first.length != second.length
      first_a, second_a = first.split(//), second.split(//)
      distance = 0
      first_a.each_index do |i|
        distance += 1 if first_a[i] != second_a[i]
      end
      distance
    end