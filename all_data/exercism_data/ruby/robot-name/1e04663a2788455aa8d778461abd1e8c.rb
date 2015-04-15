class Robot
  LETTERS = ('A'..'Z').to_a
  DIGITS = (1..9).to_a

  def initialize
    reset
  end

  attr_reader :name

  def reset
    @name = (random(2, LETTERS) + random(3, DIGITS)).join
  end


  private

  def random(count, dictionary)
    count.times.map { dictionary.sample }
  end
end
