class Robot
attr_reader :name

def initialize
  reset
end

def reset
  current = @name
  @name = (0..1).map { (65 + rand(26)).chr }.join + (0..3).map { rand(9) }.join
  reset if current == @name
end

end
