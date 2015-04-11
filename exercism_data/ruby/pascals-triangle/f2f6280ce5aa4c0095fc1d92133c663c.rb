class Triangle

  attr_reader :rows

  def initialize(size)
    @size = size
    @rows = []
    build_rows
  end

  def build_rows
    case @size
    when 1
      @rows = [[1]]
    when 2
      @rows = [[1],[1,1]]
    else
      @rows = [[1],[1,1]]
      (@size-2).times do
        @rows << new_row
      end
    end
  end

  def new_row
    row = [1]
    last_row.each_cons(2) do |a,b|
      row << a + b
    end
    row << 1
  end

  def last_row
    rows[-1]
  end

end
