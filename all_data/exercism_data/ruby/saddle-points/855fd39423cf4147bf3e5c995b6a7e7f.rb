class Matrix
  
  attr_accessor :rows , :columns
  
  def initialize(args)
    self.rows = []
    self.columns = []
    args.split("\n").each_with_index do |row, row_i|
      self.rows[row_i] = []
      row.split(" ").each_with_index do |col,col_i|
        self.rows[row_i] << col.to_i
        if self.columns[col_i] then 
          self.columns[col_i] << col.to_i 
        else
          self.columns[col_i] =[col.to_i]
        end
      end  
    end
  end
  

  def saddle_points
    find_max_in_rows & find_min_in_cols # array intersect
  end 
  
  def find_min_in_cols
    min_col = []
    self.columns.each_with_index do |c, col_i|
      min =  Float::INFINITY
      min_index = []
      c.each_with_index do |r, row_i|
        if r < min then
          min = r 
           min_index = [row_i]
        elsif r == min then
          min_index.push(row_i) 
        end  
      end
      min_index.map { |mi| min_col.push([mi,col_i])}
    end    
    min_col
  end   
  
  def find_max_in_rows
    max_rows = [] 
    self.rows.each_with_index do |r, row_i|
      max = - Float::INFINITY
      max_index = nil
      r.each_with_index do |c, col_i|
        if c > max then
          max = c 
          max_index = col_i
        end  
      end
      max_rows.push([row_i, max_index])
    end    
    max_rows
  end 
  
end
