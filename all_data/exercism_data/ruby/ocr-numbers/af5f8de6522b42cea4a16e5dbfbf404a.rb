class OCR
 
  def initialize(text)
    @text = text
    @rows =  @text.split("\n\n")
  end
  
  def generate_matrix(row)
    row.split("\n").to_a.map { |m| m.split("").each_slice(3).to_a }   
  end
  
  def row_digit_count(matrix)
    [matrix[0].length,matrix[1].length,matrix[2].length].max
  end

  def gen_ocr_digit(matrix,i)
    ocr_digit = []
    ocr_digit << matrix[0][i] if matrix[0][i]
    ocr_digit << matrix[1][i] if matrix[1][i]
    ocr_digit << matrix[2][i] if matrix[2][i]
    ocr_digit.join("")    
  end
    
  def convert
    number = []
    @rows.each do |row|
      matrix = generate_matrix(row)
      digits = row_digit_count(matrix)
      for i in 0..digits-1
        ocr_digit = gen_ocr_digit(matrix,i) 
        case ocr_digit
          when " _| ||_|"," _ | ||_|"
            number << "0"
          when "  |  |","     |  |"
            number << "1"
          when " _ _||_"," _  _||_ "
            number << "2"
          when " _ _| _|"," _  _| _|"  
            number << "3" 
          when "|_|  |","   |_|  |"
            number << "4" 
          when " _|_ _|"," _ |_  _|"   
            number << "5" 
          when " _|_|_|"," _ |_ |_|"  
            number << "6"
          when " _  |  |"," _   |  |" 
            number << "7"
          when " _|_||_|"," _ |_||_|"
            number << "8"
          when " _|_| _|"," _ |_| _|"
            number << "9" 
          else
            number << "?"
          end
      end 
      number << ","
    end  
    number = number[0..-2]
    number.empty? ? "?" : number.join("") 
  end
  
end
