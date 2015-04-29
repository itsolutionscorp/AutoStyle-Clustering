def compute(a, b)
    count = 0
    a.split("").zip(b.split("")).each do |first, second|

      if first && second && first != second
        count = count + 1
      end


    end
    count

  end