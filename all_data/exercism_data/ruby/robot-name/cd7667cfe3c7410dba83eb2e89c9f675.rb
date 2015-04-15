require 'set' 

class Robot
  @@used_names = Set.new
  attr_reader :name

  def initialize
    reset
  end

  def reset
    begin
       @name = random_name
    end while already_used? name
    @@used_names << name
  end

  def random_name
    (2.times.map{ random_letter } + 3.times.map{ random_digit  }).join
  end

  def random_letter
    (Random.rand(26) + "A".ord).chr
  end

  def random_digit
    (Random.rand(10) + "0".ord).chr
  end

  def already_used?(name)
    @@used_names.include? name
  end
end
