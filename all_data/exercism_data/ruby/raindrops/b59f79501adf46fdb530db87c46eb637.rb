require 'prime'

class Raindrops
	def self.convert(number)
		raindrops = []
		factor_tree = factor_tree(number)
		factor_tree.each do |factor|
			raindrops << "Pling" if factor == 3 && !raindrops.include?("Pling")
			raindrops << "Plang" if factor == 5 && !raindrops.include?("Plang")
			raindrops << "Plong" if factor == 7 && !raindrops.include?("Plong")
		end		
		return number.to_s if raindrops.empty?
		return raindrops.join
	end

	private
	def self.factor_tree(number)		
		factor_tree = []	
		factor_tree << number
		prime_check = true

		return factor_tree if number.prime?

		while prime_check
			prime_check = false
			factor_tree.each_with_index do |prime, index|
				next if prime.prime?
				Prime.each(prime) do |divider|		
					if prime % divider == 0 then
						factor_tree[index] = prime/divider
						factor_tree << divider
						prime_check = true
						break
					end
				end		
			end
		end
		return factor_tree.sort
	end
end
