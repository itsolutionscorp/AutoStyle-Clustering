class Robot
  attr_reader :name

  def initialize
    @name = (0..1).map { ('a'..'z').to_a[rand(26)] }.join.upcase + (0..2).map { rand(9) }.join
  end

  def reset
    @name = (0..1).map { ('a'..'z').to_a[rand(26)] }.join.upcase + (0..2).map { rand(9) }.join
  end

end
