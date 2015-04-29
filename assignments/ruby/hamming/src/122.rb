def compute(first, second)
    first  = first.split(//)
    long   = first.count
    secondary = second.split(//)
    second = secondary[0..long-1]

    count  = 0
    second.each_with_index do |letter, index|
    count += 1 if letter != first[index]
    end

    count
  end