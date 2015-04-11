class Raindrops
	def self.convert(num)
    string = ''
    if num%3 == 0
      string += 'Pling'
    end
    if num%5 == 0
      string += 'Plang'
    end
    if num%7 == 0
      string += 'Plong'
    end
    string.empty? ? num.to_s : string
  end
end
