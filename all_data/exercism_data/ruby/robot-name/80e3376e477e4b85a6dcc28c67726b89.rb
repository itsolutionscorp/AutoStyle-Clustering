module NameFactory
	
	@@char_randomizer = Random.new(12345)
	@@used_names = {}

	def self.get_name
		begin
			name = ""
			2.times { |i| name << @@char_randomizer.rand(65..90).chr }
			3.times { |i| name << @@char_randomizer.rand(0..9).to_s }
		end while @@used_names.key?(name.to_sym)
		@@used_names[name.to_sym] = name
	end

end

class Robot

	include NameFactory

	def initialize
		@name = NameFactory.get_name
	end

	def name
		(@name == nil) ? @name = NameFactory.get_name : @name
	end

	def reset
		@name = nil
	end

end
