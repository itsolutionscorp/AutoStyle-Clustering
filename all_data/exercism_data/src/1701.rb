def compute(first, second)
    difference = 0
    first  = first.split(//)
    second = second.split(//)

    length = [first.length, second.length].min
    length.times do |index|
        difference += 1 if first[index] != second[index]
    end
    difference
  end