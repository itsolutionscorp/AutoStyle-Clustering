class Matrix
  def initialize(string)
    matrix = []
    string.split("\n").each do |column|
      matrix << column.split(" ")
    end
    @int_matrix =[]
    new_row = []
    matrix.each do |row|
      row.each do |letter|
        new_row << letter.to_i
      end
      @int_matrix << new_row
      new_row =[]
    end

    # split by \n, store each of those elements into our new_matrix array
    # split each element of new_matrix by ' '
    # turn each element into an int
    #
  end

  def rows
    @int_matrix
  end

  def columns
    column_array = []
    new_column = []
    @int_matrix.each do |row|
      column_counter = 0
      while column_counter < row.length
        row_counter = 0
        while row_counter < @int_matrix.length
          new_column << @int_matrix[row_counter][column_counter]
          row_counter+=1
        end
        column_array << new_column
        new_column = []
        column_counter+=1
      end
    end
    column_array
  end
end
