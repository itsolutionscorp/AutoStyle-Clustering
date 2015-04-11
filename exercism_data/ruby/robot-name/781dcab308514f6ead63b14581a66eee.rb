class Robot
  def initialize
    @name = name_generate
  end

  def name
    if @name
      return @name
    else
      initialize
    end
  end

  def reset
    @name = nil
  end

  private

  def name_generate
    num_letters = 2
    num_numbers = 3
    valid_chars = [*'A'..'Z']
    letters = Array.new(num_letters) {valid_chars.sample}.join
    numbers = (1.upto(num_numbers).map { rand(0..9)}).join
    letters + numbers
  end
end
