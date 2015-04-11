class Grains

  def square(number)
     (1..number).inject {|grains| grains * 2 }
  end

  def total
    total = (1..64).inject(0) { |total, number| total + square(number)}
  end
    
end
