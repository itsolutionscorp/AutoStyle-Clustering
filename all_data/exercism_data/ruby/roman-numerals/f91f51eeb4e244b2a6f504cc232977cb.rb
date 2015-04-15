class Integer
 @@thoudsands=["","M","MM","MMM"]
 @@hundreds=["","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"]
 @@tens=["","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"]
 @@ones=["","I","II","III","IV","V","VI","VII","VIII","IX"]

 @num
 
 def to_roman()
 puts @@thoudsands[0]
	output=""
	if self <= 3000 then 
		output+=@@thoudsands[self.to_i/1000]
		@num = self.to_i % 1000
		output+=@@hundreds[@num/100]
		@num%=100
		output+=@@tens[@num/10]
		@num%=10
		output+=@@ones[@num]
	end
	output
 end
end
