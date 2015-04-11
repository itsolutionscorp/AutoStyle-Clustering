class Robot
  def initialize
    @name = Array.new(2){(rand(122-97) + 97).chr}.join.upcase + rand(999).to_s
  end

  def name
    @name
  end

  def reset
    initialize
  end
end
