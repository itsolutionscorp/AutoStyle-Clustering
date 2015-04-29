class Hamming
    def self.compute(a, b)
       
       if a == b
           0
       end

       array_a = a.split("")
       array_b = b.split("")
       zipped_array = array_a.zip(array_b)

       zipped_array.select { |a, b|
           a && b && a != b
       }
       .length

    end
end
