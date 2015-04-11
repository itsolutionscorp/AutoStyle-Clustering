class Triplet
  
  attr_reader :a, :b, :c
  private :a, :b, :c

  def initialize(a, b, c)
    @a = a
    @b = b
    @c = c
  end

  def self.where(options={})
    max_factor = options.fetch(:max_factor)
    min_factor = options.fetch(:min_factor, 1)
    sum = options.fetch(:sum, nil)
    triplets = generate_sides(min_factor, max_factor)
      .uniq { |sides| sides.sort  }
      .map { |sides| Triplet.new(*sides) }
      .select(&:pythagorean?)
    triplets = triplets.select { |triplet| triplet.sum ==  sum } if sum
    triplets
  end

  def sum
    sides.reduce(0, :+)
  end

  def product
    sides.reduce(:*)
  end

  def pythagorean?
    a**2 + b**2 == c**2
  end

  private

    def self.generate_sides(min_factor, max_factor)
      possible_sides = []
      (min_factor..max_factor).each do |a_factor|
        (min_factor..max_factor).each do |b_factor|
          (min_factor..max_factor).each do |c_factor|
            possible_sides << [a_factor, b_factor, c_factor]
          end
        end
      end
      possible_sides
    end

    def sides
      [a, b, c]
    end

end
