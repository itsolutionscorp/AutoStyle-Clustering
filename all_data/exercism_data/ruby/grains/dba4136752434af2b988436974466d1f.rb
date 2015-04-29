class Grains
  def initialize
    @square_cache = {}
  end

  def square(squares)
    get_grains_for_square(squares)
  end

  def total
    (1..64).collect do |n|
      get_grains_for_square(n)
    end.reduce(:+)
  end

  private
  def get_grains_for_square(square)
    return cache(square) if cached?(square)

    if @square_cache.empty?
      calculate_grains(square)
    else
      cache_index = (square - (square - @square_cache.keys.max))

      calculate_grains(square, cache(cache_index), cache_index)
    end
  end

  def calculate_grains(no_of_squares, grains = 1, square = 1)
    return @square_cache[square] = grains if square == no_of_squares

    calculate_grains(no_of_squares, grains * 2, square + 1)
  end

  def cache(square)
    @square_cache[square]
  end

  def cached?(square)
    @square_cache.has_key?(square)
  end
end
