
class Grains
    def square(index)
        return 2 ** (index - 1)
    end
    
    def total
        total = 0
        (1..64).each do |index|
           total += square(index)
        end
        total
    end
end
