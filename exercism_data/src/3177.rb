def compute(arg, arg2)
        number = 0
        arg.split(//).each_with_index do |el, index|
            number += 1 if el != arg2[index]
        end
        number
    end