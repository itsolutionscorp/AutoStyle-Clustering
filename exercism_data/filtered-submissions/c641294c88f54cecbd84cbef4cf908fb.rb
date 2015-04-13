def compute(a, b)

       if a == b
           return 0
       end

       count = 0

       array_a = a.split("")
       array_b = b.split("")

       array_a.zip(array_b).each do |first, second|
           if first && second && first != second
               count += 1
           end
       end
       return count
    end