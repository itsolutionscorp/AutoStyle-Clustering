class Raindrops
	def self.convert(num)
		string = ''
		(num / 3.0) == (num / 3) ? string << 'Pling' :  nil
		(num / 5.0) == (num / 5) ? string << 'Plang' : nil
		(num / 7.0) == (num / 7) ? string << 'Plong' : nil
		string == '' ? num.to_s : string 
	end
end
