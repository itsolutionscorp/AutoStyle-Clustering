class Queens
  attr_reader :white, :black

  def initialize(args={})
    @white = args[:white] || [0,3]
    @black = args[:black] || [7,3]
    raise ArgumentError if white.eql? black
  end

  def to_s
    8.times.map do |y|
        8.times.map do |x|
          if white[0].eql? y and white[1].eql? x
            'W'
          elsif black[0].eql? y and black[1].eql? x
            'B'
          else
            '_'
          end
        end.join(" ")
    end.join("\n")
  end

  def attack?
    white[0].eql? black[0] or
      white[1].eql? black[1] or
      (white[0]-black[0]).abs.eql? (white[1]-black[1]).abs
  end
end
