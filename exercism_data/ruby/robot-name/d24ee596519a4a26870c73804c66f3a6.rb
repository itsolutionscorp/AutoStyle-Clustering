class Robot
	attr_reader :name

	@@names_used = []

	def initialize
		@name = random_unique_name
	end

	def reset
		@name = random_unique_name
	end

	private

		def random_unique_name
			name = ''
			begin
				chars  = (0...2).map { random_char }.join('')
				digits = (0...3).map { rand(10) }.join('')
				name = chars << digits
			end while @@names_used.include?(name)

			@@names_used.delete(@name)
			@@names_used.push(name)
			
			name
		end

		def random_char
			(65 + rand(26)).chr
		end
end
