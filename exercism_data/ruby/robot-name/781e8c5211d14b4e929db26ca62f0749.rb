class Robot

	@@used_names = []
	attr_reader :name

	def initialize
		make_name
	end

	def reset
		@name = ''
	end

	def name
  		  make_name if @name.empty?
  		  @name
	end
	
	private
	def make_name
  		  @name = random_name
		  while(@@used_names.include?(@name))
		    @name = random_name
		  end
		  @@used_names << @name
	end
	def random_name
		'AB' + Random.new.rand(100..1000).to_s
	end
end
