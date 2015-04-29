class Raindrops
	def self.convert(n)
		speak = ''
		n % 3 == 0 ? speak += 'Pling' : nil
		n % 5 == 0 ? speak += 'Plang' : nil
		n % 7 == 0 ? speak += 'Plong' : nil
		speak == '' ? n.to_s : speak
	end
end
