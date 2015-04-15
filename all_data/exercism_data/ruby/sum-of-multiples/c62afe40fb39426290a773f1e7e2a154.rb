class SumOfMultiples

	attr_accessor :args

	def initialize (*args)
		args.empty? ? @args = [3,5] : @args = args.to_a
	end

	def to (max)
		list = []
		@args.each do |i|
			(0...max).step(i) {|mult| list << mult}
		end
		list.uniq.reduce(:+)
	end

	def self.to(max, args=[3,5])
	    new(*args).to(max)
	end

end
