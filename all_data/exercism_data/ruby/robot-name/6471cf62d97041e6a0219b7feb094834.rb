class Robot
  attr_reader :name

  CHARS = 'A'..'Z'
  NUMS  = '0'..'1'

  def initialize
    reset
  end

  def reset
    @name = gen_name
  end

  private
  def gen_name
    name = ""
    2.times { name << rand_rng(CHARS) }
    3.times { name << rand_rng(NUMS ) }
    name
  end
end

module Kernel
  def rand_rng(rng)
    rng.to_a.sample
  end
end
