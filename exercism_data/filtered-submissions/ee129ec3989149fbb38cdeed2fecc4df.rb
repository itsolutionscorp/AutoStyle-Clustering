def compute(a, b)
    original = a.split("")
    other = b.split("")


    until original.size == other.size
      if original.size > other.size
        original.pop
      elsif other.size > original.size
        other.pop
      end
    end

    count = 0


    (0..(original.length-1)).each do |na|
        if original[na] != other[na]
          count += 1
        end
    end
    count
  end