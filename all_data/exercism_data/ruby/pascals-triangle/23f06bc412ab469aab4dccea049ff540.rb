class Triangle
  
  attr_accessor :rows
  
  def initialize(count)
    self.rows = []
    count.times do |i|
      self.rows << gen_row(self.rows.last)
    end
  end
  
  def gen_row(prev_row)  
    return [1] unless prev_row
    row = []
    prev_row.length.times do |i|
      if i == 0
        row << prev_row[i]
      else
        row << prev_row[i] + prev_row.fetch(i-1,0)
      end
    end
    row << prev_row.last
  end
  
end
