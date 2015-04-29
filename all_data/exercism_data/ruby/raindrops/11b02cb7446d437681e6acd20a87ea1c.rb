class Raindrops
  FACTORS = [3, 5, 7]
  NOISES = %w(Pling Plang Plong)

  def self.convert(x)
    return x.to_s if FACTORS.none? { |y| entire_division?(x, y) }

    words = FACTORS.each_with_index.map do |y, i|
      if entire_division?(x, y)
        NOISES[i]
      end
    end

    words.join
  end

  private

  def self.entire_division?(a, b)
    a % b == 0
  end
end
