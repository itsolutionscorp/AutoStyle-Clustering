class Robot
  attr_reader :name

  @@names = []

  def initialize
    @name = reset
  end

  def reset
    @name = set_name
  end

  private

  LETTERS = ('A'..'Z').to_a
  NUMBERS = (0..9).to_a

  def make_name
    letters = LETTERS.sample(2)
    numbers = NUMBERS.sample(3)
    (letters + numbers).join
  end

  def set_name
    new_name = make_name
    if @@names.include? new_name
      new_name = set_name
    else
      @@names << new_name
    end
    new_name
  end

end
