class Robot
	attr_accessor :name
	def initialize
		#o = [('a'..'z'), ('A'..'Z')].map { |i| i.to_a }.flatten
		##@name = (0...50).map { o[rand(o.length)] }.join
		num = [1,2,3,4,5,6,7,8,9,0]
		@name =""
		ch = [('a'..'z'),('A'..'Z')].map{|i| i.to_a }.flatten
		2.times{ @name << ch[rand(ch.length)]}
		3.times{ @name << num[rand(9)].to_s }

		p @name
	end

	def reset
		num = [1,2,3,4,5,6,7,8,9,0]
		@name =""
		ch = [('a'..'z'),('A'..'Z')].map{|i| i.to_a }.flatten
		2.times{ @name << ch[rand(ch.length)]}
		3.times{ @name << num[rand(9)].to_s }
	end
end
