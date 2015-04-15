class Grains

  def square number
    calculate_grains_on_square number
  end

  def total
    1.upto(64).reduce do |sum, number|
      sum += calculate_grains_on_square number
    end
  end

private
  
  def calculate_grains_on_square number
    number.pred.times.reduce( '1' ) { |string| string << '0' }.to_i( 2 )
  end

end
