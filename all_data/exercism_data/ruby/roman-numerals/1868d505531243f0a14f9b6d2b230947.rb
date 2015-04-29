        class Fixnum

	def self.included(base)
		base.extend(ClassMethods)
	end

	def to_roman
		decimalMap = {1000=>"M", 900=>"CM", 500=>"D", 400=>"CD", 100=>"C", 90=>"XC", 50=>"L", 40=>"XL", 10=>"X", 9=>"IX", 5=>"V", 4=>"IV", 1=>"I"}

		workingValue = self		
		romainNumberal = ""

		decimalMap.each_with_object ({}) do |(key, value)|
			until (workingValue / key) == 0 do
				romainNumberal << value
				workingValue -= key				
			end
		end

		romainNumberal
	end
	
end
      
