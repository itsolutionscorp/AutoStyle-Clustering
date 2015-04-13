def compute( s1, s2 )
    s1array = s1.split("")
    s2array = s2.split("")
    length = s1array.length
    count = 0
    if s1array.length > s2array.length
        length = s2array.length
    end
    if s1array.each_with_index { |item, index|
        if index < length && s2array[index] != item
          count += 1
        end
      }
    end
    count
  end