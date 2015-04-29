class Series

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence.split("").map(&:to_i)
  end

  def slices(size)
    check_invalid(size)
    rotations(size).times.collect do
      slice = sequence.take(size)
      @sequence = sequence.rotate
      slice
    end
  end

  private

  def rotations(size)
    sequence.length - (size - 1)
  end

  def check_invalid(size)
    raise ArgumentError, "Invalid size" unless valid?(size)
  end

  def valid?(size)
    sequence.length >= size
  end

end
