class Raindrops
	def self.convert(num)
		string = ''
		(num % 3) == 0  ? string << 'Pling' :  nil
		(num % 5) == 0  ? string << 'Plang' : nil
		(num % 7) == 0 ? string << 'Plong' : nil
		string == '' ? num.to_s : string 
	end
end
