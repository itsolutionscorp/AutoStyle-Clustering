require 'prime'
# Output raindrops
class Raindrops
  def self.convert(num)
    output, primer = [], Prime.instance
    primes = primer.prime_division(num)
    primes.each do |p|
      text =
        case p[0]
        when 3 then 'Pling'
        when 5 then 'Plang'
        when 7 then 'Plong'
        else nil
        end
      output.push text unless text.nil?
    end
    output.empty? ? num.to_s : output.join
  end
end
