class Robot
  
  attr_accessor :name

  def initialize
  	gen_new_name
  end

  def reset
  	gen_new_name
  end

  def gen_new_name
    @name = "#{get_random_char + get_random_char}#{get_random_number + get_random_number + get_random_number}"
  end

  def get_random_char
    (65 + Random.rand(25)).chr
  end

  def get_random_number
    Random.rand(10).to_s
  end

  private :get_random_number, :get_random_char, :gen_new_name
end
