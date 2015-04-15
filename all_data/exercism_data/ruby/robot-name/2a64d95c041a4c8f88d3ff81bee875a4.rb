class Robot
 def self.new
 @name=(("a".."z").to_a+("A".."Z").to_a)[rand(52)]+(("a".."z").to_a+("A".."Z").to_a)[rand(52)]+(0..9).to_a[rand(10)].to_s+(0..9).to_a[rand(10)].to_s+(0..9).to_a[rand(10)].to_s
 self
 end

 def self.name
 @name.to_s
 end
 
 def self.reset
 @name=Robot.new.name
 end
end
