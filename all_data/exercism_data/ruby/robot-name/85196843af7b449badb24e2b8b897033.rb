class Robot
  def initialize
    @name = generate_name
  end

  def name
    @name
  end

  def generate_name
    letters = ('A'..'Z').to_a
    digits  = (0..9).to_a

    name = ''
    2.times do
      name += letters.sample
    end
    3.times do
      name += digits.sample.to_s
    end

    name
  end

  def reset
    @name = generate_name
  end
end
