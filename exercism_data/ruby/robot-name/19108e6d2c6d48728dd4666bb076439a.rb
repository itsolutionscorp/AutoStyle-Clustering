class Robot
  attr_reader :name

  def initialize
    @name = random_name
  end

  def random_name
    random_char + random_char + random_number.to_s
  end

  def random_number
    num = rand(999)
    num = '%03i' % num
  end

  def random_char
    alphabet = Hash.new
    alphabet = ("A".."Z").map {|i| i}
    alphabet[rand(25)]
  end

  def reset
    @name = random_name
  end
end
