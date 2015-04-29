def compute(first, second)
    count = 0
    first.each_char.with_index(0) do |char, i|
      if x = second.split(//)[i] != char
        count += 1
      else
      end
    end

    return count
  end