        class Fixnum

	def self.included(base)
		base.extend(ClassMethods)
	end

	def to_roman
		decimalMap = {1 => 'I', 4 => 'IV', 5 => 'V', 9=>'IX', 10 =>'X', 
			40 => 'XL', 50 => 'L', 90 => 'XC', 100 => 'C', 400 => 'CD', 500 => 'D', 900 => 'CM', 1000 => 'M'}

		workingValue = self
		romainNumberal = ""

		#reverse the hash
		myHash = Hash[decimalMap.to_a.reverse]

		myHash.each_with_object ({}) do |(key, value)|
			until (workingValue / key) == 0 do
				romainNumberal << value
				workingValue -= key				
			end
		end

		# until self <= 0 do
		# 	#get the maximum value in the keys
		# 	maxInteger = decimalMap.keys.select{|d| d<= workingValue}.max
		# 	romainNumberal << decimalMap[maxInteger]
		# 	workingValue = workingValue - maxInteger
		# end

		romainNumberal
	end
	
end
      
