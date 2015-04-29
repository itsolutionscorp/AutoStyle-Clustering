class Bst
	attr_accessor :data, :left, :right

	def initialize(n)
		@data = n
	end

	def insert(n)
		n <= data ? insert_in_direction(n, :left) : insert_in_direction(n, :right)
	end

	def each(&block)
		[:left, :data, :right].map { |symbol| each_yielder(symbol, &block) }.flatten
	end

	private

		def insert_in_direction(n, dir)
			send(dir) ? send(dir).insert(n) : create_node_in_direction(n, dir)
		end

		def create_node_in_direction(n, dir)
			send(dir.to_s << "=", Bst.new(n))
		end

		def each_yielder(symbol, &block)
			if [:left, :right].include? symbol
				side = send(symbol)
				side ? side.each(&block) : []
			else
				yield send(symbol)
			end
		end
end
