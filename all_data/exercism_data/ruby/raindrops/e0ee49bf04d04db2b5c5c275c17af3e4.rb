class Raindrops
	def self.convert(num)
		tests = Hash["Pling" => mod_zero(num, 3), "Plang" => mod_zero(num, 5), "Plong" => mod_zero(num, 7)]
	
		tests.keep_if {|k,v| v}
	
		(tests.length == 0) ? num.to_s : tests.keys.join()
	end

	private
	def self.mod_zero(n, d)
		(d != 0) ? (n % d == 0) : false
	end
end
