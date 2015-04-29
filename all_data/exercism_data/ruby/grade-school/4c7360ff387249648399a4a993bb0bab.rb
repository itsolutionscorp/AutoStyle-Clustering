class School

	def initialize
		@hash = Hash.new
	end

	def add(value, key)
		if !@hash[key]
			@hash.store(key, [])
		end
		@hash[key] << value
		@hash[key].sort!
	end

	def to_hash
		return sort_hash(@hash)
	end

	def grade(key)
		return @hash[key] || []
	end

	private

	def sort_hash(hash)
		sorted_hash = {}
		hash.keys.sort.map {|key| sorted_hash.store(key, @hash[key])} #keep hash in grade order
		sorted_hash
	end

end
