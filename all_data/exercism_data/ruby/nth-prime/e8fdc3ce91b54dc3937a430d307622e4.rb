class Prime
	@@list = [2,3]
	def self.nth n
		raise ArgumentError unless n > 0
		# generate more primes
		while @@list.length < n
			add_to_list
		end
		@@list[n-1]
	end
	
	private
	def self.add_to_list
		new_list = @@list + Array((@@list.last + 1)..105000)
		new_list.each do |outer|
			new_list.reject! {|inner| inner % outer == 0 and inner != outer}
		end
		@@list = new_list
	end
end
