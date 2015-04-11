class Grains 

    def total
       square(65) - 1 
    end

    def square(num)
        (2..num).inject(1){ |sum| sum *= 2 }
    end
    
end
