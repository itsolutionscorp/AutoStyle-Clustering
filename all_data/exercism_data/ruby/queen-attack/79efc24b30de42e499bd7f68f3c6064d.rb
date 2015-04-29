class Queens
  attr_reader :white, :black

  def initialize(*args)
    @white = args.empty? ? [0, 3] : args[0][:white]
    @black = args.empty? ? [7, 3] : args[0][:black]

    raise ArgumentError if @white == @black
  end

  def to_s
    table = ""
    8.times do |x|
      8.times do |y|
        case
        when [x,y] == @white then table << "W "
        when [x,y] == @black then table << "B "
        else table << "O "
        end
      end
      table.strip! << "\n"
    end
    table.chomp
  end

  def attack?
    return true if same_row?
    return true if same_column?
    return true if same_diagonal?
    false
  end

  private

  def same_row?
    @white[0] == @black[0]
  end

  def same_column?
    @white[1] == @black[1]
  end

  def same_diagonal?
    diagonal_1 = @white[0] - @white[1] == @black[0] - @black[1]
    diagonal_2 = @white[0] + @white[1] == @black[0] + @black[1]
    diagonal_1 || diagonal_2
  end
end
