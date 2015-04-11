class Matrix

  def initialize (num)
    @num = num
  end

  def rows
    row_array = []
    numbers = @num.split("\n")
    numbers.each { |pair| row_array << pair.split(" ") }
    row_array.map do |pair|
      pair.map do |letter|
        letter.to_i
      end
    end
  end

  def columns
    rows.transpose
  end
  end

   
