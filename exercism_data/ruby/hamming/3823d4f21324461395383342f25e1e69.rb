class Hamming

  def self.compute(origin, copy)
    self.new(origin, copy).call
  end

  def initialize(origin, copy)
    @origin, @copy = origin, copy
  end

  def call
    shortest_length.times.count do |i|
      origin_array[i] != copy_array[i]
    end
  end

  private
  def shortest_length
    [origin_array, copy_array].min.length
  end

  def origin_array
    @origin.split('')
  end

  def copy_array
    @copy.split('')
  end
end
