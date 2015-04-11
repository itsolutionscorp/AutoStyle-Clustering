class Robot
  attr_reader :name

  @@r = Random.new

  def initialize
    reset
  end

  def reset
    c1 = random_uppercase_char
    c2 = random_uppercase_char
    n = random_3_digit_number
    @name = "#{c1}#{c2}#{n}"
  end

  def random_uppercase_char
    (@@r.rand(26) + 65).ord
  end

  def random_3_digit_number
    "%03d" % @@r.rand(1000)
  end
end
