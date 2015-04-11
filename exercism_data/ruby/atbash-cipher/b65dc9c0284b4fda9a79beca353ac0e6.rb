class Atbash
	
	def self.encoding_chart
		abc = %w{a b c d e f g h i j k l m n o p q r s t u v w x y z}
		encoding_chart = {}
		abc.each_with_index do |char, index|
			encoding_chart.merge!({abc[index] => abc[abc.count - 1 - index]})
		end
		encoding_chart
	end
	
	ENCODING_CHART = self.encoding_chart
	
	def self.encode(string)
		encoded_string = ''
		arr = string.downcase.split(%r{\s*})
		i = 0
		arr.each do |char|
			if /[a-z0-9]/.match(char) != nil then
				i += 1
				if /[0-9]/.match(char) != nil
					encoded_string += char
				else
					encoded_string += ENCODING_CHART[char] 
				end
				if 	i % 5 == 0
					encoded_string += ' '
				end
			end
		end
		encoded_string = encoded_string.chomp(' ')
	end

end
