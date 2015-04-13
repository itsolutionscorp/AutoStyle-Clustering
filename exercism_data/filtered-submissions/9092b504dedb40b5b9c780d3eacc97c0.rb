def compute(strand_one, strand_two)
   one = strand_one.chars
   two = strand_two.chars
   count = 0

    one.each_with_index do |letter, index |
      if letter == two[index]
        count += 0

      else
        count += 1

      end

    end
    count
    end