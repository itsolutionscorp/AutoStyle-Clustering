class Integer < Numeric

	def to_roman
		romans=[['','M','MM','MMM'],
			   ['','C','CC','CCC','CD','D','DC','DCC','DCCC','CM'],
		       ['','X','XX','XXX','XL','L','LX','LXX','LXXX', 'XC'],
		       ['','I','II','III','IV','V','VI','VII','VIII','IX']]
		rom_num = ''
		num_str = self.to_s.rjust(4,'0')
		
		0..4.times do |n| 
			rom_num << romans[n][num_str[n].to_i]
		end
		rom_num
	end
	
end
