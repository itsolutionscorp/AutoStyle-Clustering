class Raindrops
  require 'prime'

  def self.convert(num)
    prime_factors = num.prime_division
                       .reduce([]) { |arr, val| arr.push(val[0]) }

    useful_primes = prime_factors & [3,5,7]

    if useful_primes == []
      num.to_s
    else
      p = useful_primes.map do |v|
        case v
          when 3 then 'Pling'
          when 5 then 'Plang'
          when 7 then 'Plong'
        end
      end

      p.join
    end
  end

  private

  # def convert_vals(arr)
  #   arr.map do |v|
  #     case v
  #       when 3 then 'Pling'
  #       when 5 then 'Plang'
  #       when 7 then 'Plong'
  #     end
  #   end
  # end

end
