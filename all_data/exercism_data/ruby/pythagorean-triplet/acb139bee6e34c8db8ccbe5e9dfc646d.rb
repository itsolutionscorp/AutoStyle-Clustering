class Triplet
  def initialize(*args)
    @x, @y, @z = args[0], args[1], args[2]
  end

  def sum
    angles.inject(&:+)
  end

  def product
    angles.inject(&:*)
  end

  def pythagorean?
    @x ** 2 + @y ** 2 == @z ** 2
  end

  def self.where(*options)
    TripletCollection.where(options)
  end

  private

  def angles
    [@x, @y, @z]
  end
end

class TripletCollection
  def self.where(options)
    new(*options).matching_collections
  end

  def initialize(max_factor: 1, min_factor: 1, sum: nil)
    @range = *(min_factor..max_factor)
    @sum = sum
  end

  def matching_collections
    return find_triplets unless @sum

    find_triplets.select { |t| t.sum == @sum }
  end

  def find_triplets
    @triplets ||= @range.combination(3).select do |x, y, z|
      Triplet.new(x, y, z).pythagorean?
    end
    .map do |angles|
      Triplet.new(*angles)
    end
  end
end
