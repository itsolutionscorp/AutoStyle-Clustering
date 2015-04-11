class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    "#{random_letter.call}#{random_letter.call}#{random_number.call}#{random_number.call}#{random_number.call}"
  end

  def random_letter
    return Proc.new { ('A'..'Z').to_a.sample }
  end

  def random_number
    return Proc.new { (0..9).to_a.sample }
  end
end
