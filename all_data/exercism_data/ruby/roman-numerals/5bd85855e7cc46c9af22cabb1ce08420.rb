module RomanNumerable

	def to_roman
		ones = ['','I','II','III','IV','V','VI','VII','VIII','IX']
		tens = ['','X','XX','XXX','XL','L','LX','LXX','LXXX', 'XC']
		hund = ['','C','CC','CCC','CD','D','DC','DCC','DCCC','CM']
		thous = ['','M','MM','MMM']

		rom_num = ''
		num_str = self.to_s.rjust(4,'0')
		
		rom_num << thous[num_str[0].to_i]
		rom_num << hund[num_str[1].to_i]
		rom_num << tens[num_str[2].to_i]
		rom_num << ones[num_str[3].to_i]

		rom_num
	end
end

class Integer
	include RomanNumerable
end
