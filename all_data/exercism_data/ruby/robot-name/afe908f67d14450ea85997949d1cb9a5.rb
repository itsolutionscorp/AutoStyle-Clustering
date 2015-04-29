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
    letters = Array.new(2){[*'A'..'Z'].sample}.join
    digits = Array.new(3){[*0..9].sample}.join
    letters + digits
  end
end
