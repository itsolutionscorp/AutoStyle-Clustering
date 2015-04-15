class Integer < Numeric
	@@n = [1000,900,500,400,100,90,50,40,10,9,5,4,1,0]
	@@n2r = { 1000=>'M', 900=>'CM', 500=>'D', 400=>'CD',
			   100=>'C',  90=>'XC',  50=>'L',  40=>'XL',
		        10=>'X',   9=>'IX',   5=>'V',   4=>'IV', 1=>'I', 0=>'' }
		          
	def to_roman
		if @@n.include?(self)
			@@n2r[self]
		else #build string left to right recursively
			@@n.each { |x| self > x ? (return @@n2r[x] + (self-x).to_roman) : nil }
		end
	end
end
