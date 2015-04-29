class Triangle

  attr_reader :num_rows

  def initialize(num_rows)
    @num_rows = num_rows 
  end

  def rows
    (0..num_rows-1).to_a.map do |row|
      triangle_builder(row)
    end
  end

  private
  def triangle_builder(num_rows)
    return [1] if num_rows == 0

    previous = triangle_builder(num_rows-1)    
    middle_array = []
    
    (previous.length-1).times do |i|
      middle_array << (previous[i] + previous[i+1])
    end
    
    [1, middle_array, 1].flatten
  end

end
