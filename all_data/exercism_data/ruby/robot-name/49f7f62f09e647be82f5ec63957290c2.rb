class Robot
  attr_reader :name
  first = ('A'..'Z').to_a.shuffle
  rest = [('A'..'Z').to_a.shuffle] + 3.times.map { ('0'..'9').to_a.shuffle }
  @@random_names = Enumerator.new do |yielder|
    first.product(*rest) { |chars| yielder << chars.join }
  end

  def initialize
    @name = @@random_names.next
  end

  def reset
    @name = @@random_names.next
    self
  end
end
