class Proverb
  def initialize(*things_lost)
    options          = things_lost.last.kind_of?(Hash) ? things_lost.pop : Hash.new
    self.things_lost = things_lost
    self.qualifier   = options[:qualifier]
  end

  def to_s
    [*things_lost.each_cons(2)
                 .map { |a, b| "For want of a #{a} the #{b} was lost." },
     "And all for the want of a #{qualifier ? qualifier + ' ' : ''}#{things_lost.first}."
    ].join("\n")
  end

  private

  attr_accessor :things_lost, :qualifier
end
