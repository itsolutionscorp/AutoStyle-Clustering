require 'prime'
class Raindrops
  def self.convert(num)
   rt = []
   prime_factors = Prime.prime_division(num).flat_map { |factor, power| [factor] * power }
   if prime_factors.include?(3)
		 rt << 'Pling'
	 elsif prime_factors.include?(5)
		 rt << 'Plang'
	 elsif prime_factors.include?(7)
		 rt << 'Plong'
	 end
	 if rt.size == 0
		 num.to_s
	 else
		 rt.join
	 end
	end
end
