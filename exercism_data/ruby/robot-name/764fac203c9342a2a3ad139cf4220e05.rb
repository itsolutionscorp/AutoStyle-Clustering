class Robot
	attr_accessor :name
	def initialize
		naming
	end

	def reset
		naming
	end

	private

	def naming
		o = [('A'..'Z')].map { |i| i.to_a }.flatten
		i = [('0'..'9')].map { |i| i.to_a }.flatten
		@name = (0...2).map { o[rand(o.length)] }.join +
					(0...3).map { i[rand(i.length)] }.join
	end
end
