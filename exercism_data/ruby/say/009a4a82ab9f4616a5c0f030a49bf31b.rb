ONES = [nil, 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 
	'ten', 'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']

TENS = [nil, nil, 'twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']

GROUPS = [nil, 'thousand', 'million', 'billion']

class Say
	def initialize(number)
		raise(ArgumentError) if number < 0 || number >= 1E12

		@number = number
	end

	def in_english()
		english = ''

		group = 0
		number = @number
		while number > 0 do
			chunk = number % 1000

			chunk = translate(chunk, group)
			english = chunk + ' ' + english if !chunk.empty?

			number /= 1000
			group += 1
		end

		return (english.empty?) ? 'zero' : english.strip
	end

	private

	def translate(chunk, group)
		raise(ArgumentError, 'Invalid chunk size!') if chunk > 999

		result = ''

		hundreds, tens = chunk.divmod(100)
		result += "#{ONES[hundreds]} hundred " if hundreds > 0

		if tens < ONES.size
			result += "#{ONES[tens]} " if tens > 0
		else
			tens, ones = tens.divmod(10)
			result += "#{TENS[tens]}" if tens > 0
			result += (tens > 0 && ones > 0) ? '-' : ' '
			result += "#{ONES[ones]} " if ones > 0
		end 

		result += GROUPS[group] if group > 0 && !result.empty?
		
		result.strip
	end
end
