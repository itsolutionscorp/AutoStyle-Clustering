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
  
  
end
