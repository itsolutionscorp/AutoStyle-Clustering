def compute(string1, string2)
        counter = 0
        array1 = string1.split('').to_a
        array2 = string2.split('').to_a
        if array1.length > array2.length 
            array1.pop 
        elsif 
            array1.length < array2.length 
            array2.pop
        else
            truefalse = array1.zip(array2).map { |x, y| x == y }
            truefalse.each do |this|
                if this == false
                    counter = counter + 1
                end 
        end 
        end
        return counter
    end