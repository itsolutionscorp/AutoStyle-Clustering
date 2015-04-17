class Triplet
  def initialize(x1, x2, x3)
    @x1, @x2, @x3 = x1, x2, x3
  end

  def sum
    @x1 + @x2 + @x3
  end

  def product
    @x1 * @x2 * @x3
  end

  def pythagorean?
    @x1**2 + @x2**2 == @x3**2
  end

  def self.where(options = {})
    results = []

      max = options.fetch(:max_factor)
      min = options.fetch(:min_factor, 1)
      sum = options.fetch(:sum, nil)

      (min..max).each do |i|
        x1 = i
        (i..max).each do |j|
          x2 = j
          (j..max).each do |k|
            x3 = k
            test = Triplet.new(i, j, k)
            if test.pythagorean? 
              if sum
                results << test unless test.sum != sum
              else
                results << Triplet.new(i, j, k)
              end
            end
          end
        end
      end

    results
  end

end