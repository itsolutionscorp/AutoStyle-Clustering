class CustomSet
	def initialize(values=[])
		@data = []
		values.each{|v| put(v)}
	end
	
	def empty()
		@data.clear
		self
	end
	
	def ==(object)
		self.class.equal?(object.class) && object.data.eql?(data)
	end
	
	def size()
		@data.size
	end
	
	def to_a()
		@data.dup
	end
	
	def put(value)
		@data << value unless member?(value)
		@data.sort!
		self
	end
	
	def delete(value)
		@data.delete_if{|v| value.eql?(v)}
		self
	end
	
	def member?(value)
		@data.any?{|v| value.eql?(v)}
	end
	
	def disjoint?(set2)
		@data.none?{|v| set2.member?(v)}
	end
	
	def subset?(set2)
		set2.data.all?{|v| member?(v)}
	end
	
	def difference(set2)
		self.class.new( @data.reject{|v| set2.member?(v)} )
	end
	
	def intersection(set2)
		self.class.new( @data.select{|v| set2.member?(v)} )
	end
	
	def union(set2)
		self.class.new( @data + set2.data )
	end
	
	protected
	
	attr_reader :data
end
