require 'prime'
class Raindrops
	def self.convert(value)
		arr = []
		str = []
		Prime.prime_division(value).each{ |x| x.each{ |y| arr << y}}
		arr.each do |v|
			case v
				when 3 then (str << 'Pling')
				when 5 then (str << 'Plang')
				when 7 then (str << 'Plong')
			else
				next
			end
		end
		if str.empty?
			value.to_s
		else
			str.uniq.join
		end
 	end
end
