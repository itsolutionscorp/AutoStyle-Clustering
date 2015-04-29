class Fixnum
	
	def to_roman
		romans = {1000=>"M", 900=>"CM", 500=>"D", 400=>"CD", 100=>"C", 90=>"XC", 50=>"L", 40=>"XL", 10=>"X", 9=>"IX", 5=>"V", 4=>"IV", 1=>"I"}
		
		if romans[self]
			romans[self]
		else
			roman = ""
			num = self
			romans.each do |key, value|
				while (num>=key) do
					roman << value
					num -= key
				end
			end
			return roman
		end

	end

end
