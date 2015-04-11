class Robot

  def initialize
    reset
  end

  def reset
    random_letters = ('A'..'Z').to_a.sample(2).join('')
    @name = "%s%03d" % [random_letters, rand(1000)]
  end

  attr_reader :name

end
