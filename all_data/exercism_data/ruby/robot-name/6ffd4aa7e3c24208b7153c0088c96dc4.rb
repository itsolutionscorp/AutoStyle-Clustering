class Robot
  attr_reader :name

  @@names = []

  def initialize
    reset
  end

  def reset
    @name = [
      random_letters,
      random_numbers
    ].join

    if @@names.include?(@name)
      reset
    else
      @@names << @name
    end
  end

  private

  def random_letters
    ('A'..'Z').to_a.sample(2)
  end

  def random_numbers
    (0..9).to_a.sample(3)
  end
end
