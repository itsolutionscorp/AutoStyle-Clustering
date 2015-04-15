class Robot
  attr_reader :name

  def initialize
    @name = random_name
  end

  def reset
    initialize
  end

  private

  def random_name
    (0..1).map { ("A".."Z").to_a.sample }.join + (0..2).map { rand(10) }.join
  end
end
