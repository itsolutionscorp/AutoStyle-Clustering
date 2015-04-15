class Grains
  def initialize
    @squares = 64
    @square_hash ||= create_square_hash
  end

  def create_square_hash
    square_hash = {}
    key = 1
    value = 1
    (@squares).times do 
      square_hash[key] = value
      key += 1
      value *= 2
    end
    square_hash
  end

  def square(square)
    # running_total = 1
    # (square-1).times { running_total *= 2 }
    # running_total
    @square_hash.fetch(square)
  end

  def total
    # total = 0
    # (1..@squares).each do |num|
    #   total += square(num)
    # end
    # total

    # Create a new Hash with the key equal to the square number
    # and the value equal to the result of square
    @square_hash.values.reduce(:+)
  end
end
