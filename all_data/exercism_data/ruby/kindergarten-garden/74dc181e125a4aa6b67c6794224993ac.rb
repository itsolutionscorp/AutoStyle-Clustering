class Garden
	CHILDREN = ['Alice',
							'Bob',
							'Charlie',
							'David',
							'Eve',
							'Fred',
							'Ginny',
							'Harriet',
							'Ileana',
							'Joseph', 
							'Kincaid',
							'Larry' ]
							
	PLANTS = { 	'C' => :clover,
							'G'	=> :grass,
							'R' => :radishes,
							'V' => :violets }

	def initialize(garden, students = CHILDREN)
		@garden = garden.split("\n")
		@students = students.sort
		
		@students.each_with_index do |kid, idx|
			singleton = class << self; self end
			singleton.send(:define_method, kid.downcase.to_sym) do
				[ PLANTS[@garden[0][idx*2]], 
					PLANTS[@garden[0][idx*2+1]], 
					PLANTS[@garden[1][idx*2]],
					PLANTS[@garden[1][idx*2+1]] ]
			end
		end				
	end
end
