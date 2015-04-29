require 'prime'

class Raindrops
       
    def self.convert(num_in)
            description_arr = []
            description_str = ""

            pf_arr = list_prime_factors(num_in)

            for i in pf_arr
                description_arr << 'Pling' if i == 3
                description_arr << 'Plang' if i == 5
                description_arr << 'Plong' if i == 7
            end

           
            if description_arr.length > 0
                description_str = description_arr.join
            else
               description_str << num_in.to_s
            end
        description_str
    end
    
    def self.list_prime_factors(num_in)
        prime_factors_arr = []
        2.upto(7) do |x|
            prime_factors_arr << x if (Prime.prime? x) && (num_in % x == 0)
        end 
        prime_factors_arr
    end
    
end
