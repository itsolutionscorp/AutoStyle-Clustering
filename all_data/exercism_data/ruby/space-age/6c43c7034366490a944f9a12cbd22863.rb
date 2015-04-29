class SpaceAge

	attr_reader :seconds

	@@PLANETS = Hash.new

    File.open('README.md').each do |line|
    	if match = line.match(/ ([^ :]+): orbital period ([^ ]+) ([^ ]+) years/) then
    		@@PLANETS[match[1].to_s.downcase] = match[2].to_f * @@PLANETS[match[3].downcase]
    	elsif match = line.match(/ ([^ :]+): orbital period .* (\d+)/) then
    		@@PLANETS[match[1].to_s.downcase] = match[2].to_f
       	end
    end

    @@PLANETS.each do |planet, seconds|
		define_method "on_#{planet}" do
			return (@seconds / seconds).round(2)
		end
	end

	def initialize(seconds)
		@seconds = seconds
	end

end
