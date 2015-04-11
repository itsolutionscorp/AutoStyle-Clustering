class Matrix
  def initialize(string)
    @rows = string.split("\n").map{|x| x.split(' ')}.each{|x| x.map!{|y| y.to_i}}
    @columns = rows.transpose
  end

  attr_reader :rows, :columns

  def saddle_points
    positions.select do |position|
      saddle_point_check position
    end
  end

  def positions
    positions = []
    (0...(rows.count)).each do |row|
      (0...(columns.count)).each do |col|
        positions << [row,col]
      end
    end
    positions
  end

  def saddle_point_check(position)
    value = rows[position[0]][position[1]]
    rows[position[0]].all? {|test| value >= test} &&
    columns[position[1]].all? {|test| value <= test}
  end
end
