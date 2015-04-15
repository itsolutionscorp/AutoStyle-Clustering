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
   [].tap do |ary|
      text.each_line do |line|
        ary << line.split(' ').map(&:to_i)
      end
    end   
  end

end
