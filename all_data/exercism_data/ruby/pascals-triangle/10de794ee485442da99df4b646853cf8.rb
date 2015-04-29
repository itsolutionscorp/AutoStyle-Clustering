class Triangle
  def initialize(length)
    @length = length
    @rows = [[1]]
  end

  def rows
    (@length - @rows.length).times do
      @rows << build_rows(@rows.last)
    end

    @rows
  end

  private

  def build_rows(row)
    middle = [].tap do |out|
      row.each_cons(2).each { |arr| out << arr.inject(0, :+) }
    end

    [row.first, *middle.flatten, row.last]
  end
end
