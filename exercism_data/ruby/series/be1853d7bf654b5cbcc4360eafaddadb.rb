class Series
  def initialize(sequence)
    @numbers = chars_to_integers(sequence)
  end

  attr_reader :numbers

  def slices(length)
    if length > numbers.length
      raise ArgumentError, "No slices larger than sequence length"
    end
    numbers.each_cons(length).to_a
  end

  private

  def chars_to_integers(sequence)
    sequence.chars.map(&:to_i)
  end
end
