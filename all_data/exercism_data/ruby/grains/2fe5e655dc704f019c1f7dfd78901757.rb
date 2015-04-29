class Grains
  attr_reader :square

  def square(number)
      (1..number).inject(0.5) {|grains| grains * 2 }
  end

  def total
    total = (1..64).inject(0) { |total, number| total + square(number)}
    total.to_i
  end
    
end
