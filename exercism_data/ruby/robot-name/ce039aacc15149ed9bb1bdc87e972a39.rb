class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    letter_part = 2.times.map { ('A'..'Z').to_a.shuffle.first }.join
    number_part = 3.times.map { ('0'..'9').to_a.shuffle.first }.join
    @name = letter_part + number_part
  end
end
