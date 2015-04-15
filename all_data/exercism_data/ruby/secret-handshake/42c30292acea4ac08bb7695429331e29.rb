class SecretHandshake
	def initialize(number)
		@number = number
	end
	def commands
		return [] if @number.to_s.match(/[^\d]/)
		binary = @number.to_s(2).to_i
		bin_array = binary.to_s.split(//).map{|x| x.to_i}.reverse
		result = []
		bin_array.each_with_index do |elem, i|
			case 
			when i == 0 && elem == 1
				result << "wink"
			when i == 1 && elem == 1
				result << "double blink"
			when i == 2 && elem == 1
				result << "close your eyes"
			when i == 3 && elem == 1
				result << "jump"
			when i == 4 && elem ==1
				result.reverse!
			end
		end
		result
	end
end
