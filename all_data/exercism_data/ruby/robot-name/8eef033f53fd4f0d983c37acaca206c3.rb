require 'set' 

class Robot
  @@used_names = Set.new
  attr_reader :name

  def initialize
    @rg = Random.new(1234)
    reset
  end

  def reset
    begin
       @name = random_name
    end while already_used?(name)
    @@used_names << name
  end

  def random_name
    (2.times.map{ random_letter } + 3.times.map{ random_digit  }).join
  end

  def random_letter
    (random_number(26) + "A".ord).chr
  end

  def random_digit
    (random_number(10) + "0".ord).chr
  end

  def random_number(max)
    @rg.rand(max)
  end

  def already_used?(name)
    @@used_names.include? name
  end
end
