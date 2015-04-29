class Robot

  def initialize
    reset
  end

  def reset
    @name = "%s%03d" % [random_letters(2), rand(1000)]
  end

  attr_reader :name

private
  def random_letters(n)
    # Generate n random capital letters
    (1..n).collect {|i| ('A'..'Z').to_a[rand(26)] }.join('')
  end

end
