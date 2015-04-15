class Integer < Numeric
	@@n = [1000,900,500,400,100,90,50,40,10,9,5,4,1]
	@@n2r = { 1000=>'M', 900=>'CM',
		       500=>'D', 400=>'CD',
			   100=>'C',  90=>'XC',
		        50=>'L',  40=>'XL',
		        10=>'X',   9=>'IX',
		         5=>'V',   4=>'IV',
		         1=>'I' }
		          
	def to_roman
		if @@n.include?(self)
			@@n2r[self]
		elsif self==0
			''
		else
			@@n.each do |x| #build string left to right recursively
				self > x ? (return @@n2r[x] + (self-x).to_roman) : nil
			end
		end
	end
end
