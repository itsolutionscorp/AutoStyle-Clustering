def compute(a, b)

        count = 0
        a.each_char.each_with_index do |c, i|
            count += 1 if b[i] && b[i] != c           
        end 
        count
    end