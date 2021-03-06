class Robot

  attr_reader :name

  @@taken_names = []

  def initialize
    rand_letters = (1..2).map { ('A'..'Z').to_a[rand(26)] }.join
    rand_numbers = (1..3).map { rand(0..9) }.join
    @name = rand_letters + rand_numbers
    initialize if @@taken_names.include?(@name)
    @@taken_names << @name
  end

  def reset
    initialize
  end
end
