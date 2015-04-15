# letters are from caps:65 -> 90  low: 97 - 122
# numbers are from 48 to 57


class Robot
	@@names = []

	def namegen
		name = ""
		2.times{ name << rand(65..90).chr}
		3.times{ name << rand(48..57).chr}
		return name unless @@names.include?(name)
		namegen
	end

	def initialize
		@name = namegen
		@@names << @name
	end

	def name
		@name
	end

	def reset
		old_name = @name
		@@names.delete_if { |name| name == @name}
		@name = namegen
		@@names << @name
	end



end
