class Robot
 attr_reader :name

 def initialize
   @name = assign_name
 end

 def assign_name
   first_part = ('A'..'Z').to_a.sample(2).join('')
   second_part = rand(100..999).to_s
   first_part + second_part
 end

 def reset
   @name = assign_name
 end
end
