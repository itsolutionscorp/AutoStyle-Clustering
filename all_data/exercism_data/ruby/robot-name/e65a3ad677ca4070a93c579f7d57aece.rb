class Robot
  attr_reader :name

  def initialize
    @name = random_name
  end

  def reset
    @name = random_name
  end

  private

  def random_name
    chars = ('a'..'z').to_a
    digi  = (1..9).to_a

    "#{chars.shuffle.first}#{chars.shuffle.first}#{digi.shuffle.first}#{digi.shuffle.first}#{digi.shuffle.first}"
  end

end
