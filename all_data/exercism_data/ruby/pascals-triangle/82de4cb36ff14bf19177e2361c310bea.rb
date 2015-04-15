class Triangle
  attr_reader :n_rows

  def initialize(n_rows)
    @n_rows = n_rows
  end

  def rows
    (0...n_rows).map &method(:row)
  end

  private

  def row(row_index)
    (0..row_index).map {|c| m_cell(row_index, c) }
  end

  # memoized cells
  def m_cell(row_index, cell_index)
    (@cells ||= Hash.new do |h, r| 
      h[r] = Hash.new {|h1,c| h1[c] = cell(r, c) }
    end)[row_index][cell_index]
  end

  def cell(row_index, cell_index)
    return 0 if cell_index > row_index
    return 1 if cell_index == 0
    m_cell(row_index - 1, cell_index - 1) + m_cell(row_index - 1, cell_index)
  end
end
