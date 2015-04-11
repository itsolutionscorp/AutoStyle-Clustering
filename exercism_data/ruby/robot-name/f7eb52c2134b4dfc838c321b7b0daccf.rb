class Robot
 def initialize
 reset
 end

 def name
 @name.to_s
 end
 
 def reset
 @name=""
 2.times do |i|
  @name+=get_alphabet.to_s
 end
 3.times do |i|
  @name+=get_number.to_s
 end
 end

 def get_alphabet
 (("a".."z").to_a+("A".."Z").to_a)[rand(52)]
 end
 
 def get_number
 (0..9).to_a[rand(10)].to_s
 end
end
