class Matrix
  attr_reader :text, :grid

  def initialize(text)
    @text = text
    @grid ||= setup_grid
  end

  def rows
    grid
  end

  def columns
    grid.transpose
  end

  private

  def setup_grid
    text.each_line.map do |line| 
      line.split.map(&:to_i)
    end
  end

end
