class Hamming
    def self.compute(string1, string2)
        counter = 0
        array1 = string1.split('')
        array2 = string2.split('')
        if array1.length > array2.length 
            array1.pop 
        elsif 
            array1.length < array2.length 
            array2.pop
        else
            paired = array1.zip(array2).map { |x, y| x == y }
            paired.each do |check|
                if check == false
                    counter += 1
                end 
        end 
        end
        counter
    end 
end 
