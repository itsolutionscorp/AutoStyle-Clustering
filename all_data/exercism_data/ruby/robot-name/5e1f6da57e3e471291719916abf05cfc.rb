class Robot

	def initialize
		name = ""
		@name = name
	end

	def name
		if @name.empty?
			name_mill
		else
			@name
		end
	end

	def reset
		name_mill
	end

	def name_mill
		characters = { ?n => '0123456789',
		?l => 'ABCDEFGHIJKLMNOPQRSTUVWXYZ' }
		new_name = ''
			'llnnn'.each_byte do |x|
			source = characters[x.chr]
			new_name << source[rand(source.length)].chr
			end
		 name = new_name
		 @name = name
	end

end
