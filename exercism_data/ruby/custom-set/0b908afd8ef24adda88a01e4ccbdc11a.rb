require 'forwardable'

class CustomSet
	include Enumerable
	extend Forwardable

	def_delegators :@set, :each, :size, :empty?

	def initialize(elements = [])
		@set = elements.to_a.uniq.sort
	end

	def ==(other_set)
		size == other_set.size && all? { |e| other_set.member? e }
	end

	def put(element)
		@set = (@set << element).sort unless member? element
		self
	end

	def delete(element)
		@set = reject { |e| e.eql? element }
		self
	end

	def empty
		@set = []
		self
	end

	def member?(element)
		any? { |e| e.eql? element }
	end

	def difference(other_set)
		self.class.new(reject { |e| other_set.member? e })
	end

	def disjoint?(other_set)
		intersection(other_set).empty?
	end

	def intersection(other_set)
		self.class.new(select { |e| other_set.member? e })
	end

	def subset?(other_set)
		other_set.all? { |e| member? e }
	end

	def union(other_set)
		self.class.new(to_a + other_set.to_a)
	end
	
end
