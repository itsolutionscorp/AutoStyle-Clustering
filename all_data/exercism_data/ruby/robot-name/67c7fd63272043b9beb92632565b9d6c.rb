class Robot
  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    "#{random(2, letter)}#{random(3, number)}"
  end

  def letter
    ('a'..'z').sort_by { rand }.first
  end

  def number
    (0..9).sort_by { rand }.first.to_s
  end

  def random(number, value)
    number.times.inject('') do |memo|
      memo << value
    end
  end
end
