def compute(a, b)

       array_a = a.split("")
       array_b = b.split("")
       zipped_array = array_a.zip(array_b)

       zipped_array.count { |a, b|
           a && b && a != b
       }

    end