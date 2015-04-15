class Year < Struct.new(:value)
  def leap?
    (value % 4).zero? && (value % 100).nonzero? || (value % 400).zero?
  end
end
