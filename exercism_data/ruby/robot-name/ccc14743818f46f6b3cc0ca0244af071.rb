class Robot
  attr_reader :name

  def initialize
    reset
  end

  def gen_name
    letters = ('a'..'z').to_a + ('A'..'Z').to_a
    letters.sample(2).join + [rand(10), rand(10), rand(10)].reduce('') {|a, e| a + e.to_s }
  end
  
  def reset
    @name = gen_name
  end

end
