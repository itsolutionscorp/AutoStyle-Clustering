class Robot
  attr_reader :name
  def initialize
    @name = ('a'..'z').to_a.shuffle.first + ('a'..'z').to_a.shuffle.first + (1..9).to_a.shuffle.first.to_s + (1..9).to_a.shuffle.first.to_s + (1..9).to_a.shuffle.first.to_s
  end

  def reset
    @name = ('a'..'z').to_a.shuffle.first + ('a'..'z').to_a.shuffle.first + (1..9).to_a.shuffle.first.to_s + (1..9).to_a.shuffle.first.to_s + (1..9).to_a.shuffle.first.to_s
  end

  def to_s
    name
  end

end
