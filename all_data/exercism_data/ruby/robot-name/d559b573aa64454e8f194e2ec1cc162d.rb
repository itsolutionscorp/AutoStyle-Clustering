class Robot
  attr_accessor :name

  def initialize
    @name = self.random_letters + self.random_numbers
  end

  def reset
    @name = self.random_letters + self.random_numbers
  end

  def random_letters
    prng = Random.new
    first, second = prng.rand(0..25), prng.rand(0..25)
    letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    return "#{letters[first]}#{letters[second]}"
  end

  def random_numbers
    prng = Random.new
    result_string = ''
    3.times do
      result_string += prng.rand(0..9).to_s
    end
    return result_string
  end
end
