class Matrix

  def initialize (num)
    @num = num
  end
  def rows
    row_array = []
    @num = @num.split("\n")
    @num.each do |pair|
      row_array << pair.split(" ")
    end
    row_array.map! do |pair|
      pair.map! do |letter|
        letter.to_i
      end
    end
    row_array
  end

  def columns
    arr0 = []
    arr1 = []
    arr2 = []
    arr3 = []
    total_array = []

    self.rows.each do |x|
     arr0<< x[0]
     arr1<< x[1]
     arr2<< x[2]
     arr3<< x[3]
    end
    total_array<<arr0<<arr1<<arr2<<arr3
    total_array
  end
end
