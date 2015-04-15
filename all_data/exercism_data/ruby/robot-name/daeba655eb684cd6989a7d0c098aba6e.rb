class Robot
  attr_reader :name

  Digits = (0..9).to_a
  Chars  = ('a'..'z').to_a

  def initialize
    reset
  end

  def reset
    @name = "#{gen_string(2, Chars)}#{gen_string(3, Digits)}"
  end

  def gen_string(num, sample)
    num.times.map { sample.sample }.join
  end

end
