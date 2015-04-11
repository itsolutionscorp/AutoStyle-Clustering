class Queens

  attr_reader :white, :black

  def initialize args = {}
    @white = args.fetch(:white, [0, 3])
    @black = args.fetch(:black, [7, 3])
    fail ArgumentError.new if @black == @white

    @boards = ['O']*64
    @boards[grid_to_index *@black] = 'B'
    @boards[grid_to_index *@white] = 'W'
  end

  def to_s
    @boards.each_slice(8).each_with_object("") do |row, out|
      out << row.join(' ') << "\n"
    end.rstrip
  end

  def attack?
    br, bc = @black
    wr, wc = @white
    return true if br == wr || bc == wc          # same row or col?
    return true if ((br-wr)/(bc-wc)).abs == 1    # on diag?
    false
  end

  private

    def grid_to_index row, col
      row*8+col
    end
end
