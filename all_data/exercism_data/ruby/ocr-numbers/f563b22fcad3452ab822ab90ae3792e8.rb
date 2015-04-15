class OCR

  DIGIT_HEIGHT = 4
  DIGIT_WIDTH = 3

  def initialize(text)
    @text = text.gsub(/[^|_\s]/,"")
  end
  
  def convert
    results = []
    @text.lines.each_slice(DIGIT_HEIGHT) do |digit_line|
      results << convert_one_digit_line(digit_line)
    end
    results.join(",")
  end
      
  private

      def convert_one_digit_line(lines)
        digits = []
        lines.each do |line|
          line.chomp.chars.each_slice(DIGIT_WIDTH).with_index do |slice, i|
            digits[i] = (digits[i] || []) + slice
          end
        end

        digits_to_string(digits)
      end

      def digits_to_string(digits)
        digits.inject("") { |sum, digit| sum << convert_digit(digit) }
      end
      
      def convert_digit(array)
        case count_sides(array)
        when [4,2] then "0"
        when [2,0] then "1"
        when [2,3]
          return "5" if array[3] == "|"
          return "3" if array[8] == "|"
          "2"
        when [3,1] then "4"
        when [3,3]
          return "9" if array[5] == "|"
          "6"
        when [2,1] then "7"
        when [4,3] then "8"
        else "?"
        end
      end
      
      def count_sides(array)
        result = []
        result << array.count{|x| x == "|"}
        result << array.count{|x| x == "_"}
      end
end
