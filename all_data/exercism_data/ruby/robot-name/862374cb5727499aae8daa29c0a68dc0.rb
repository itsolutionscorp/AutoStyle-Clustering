class Robot
  attr_accessor :name

  def initialize
    @name = generate_new_name
  end

  def reset
    initialize
  end

  def generate_new_name
    alphabet = ('a'..'z').to_a.push(('A'..'Z').to_a)
    (2.times.map { alphabet.sample } + 3.times.map { rand(10) }).join
  end
end
