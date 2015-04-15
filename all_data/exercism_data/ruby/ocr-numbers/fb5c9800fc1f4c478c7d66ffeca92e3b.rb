class OCR
  NUM = ["_ | ||_|", "|  |", "_  _||_", "_  _| _|", "|_|  |",
         "_ |_  _|", "_ |_ |_|", "_   |  |", "_ |_||_|", "_ |_| _|"]

  def initialize(input)
    @text = input
  end

  def convert
    result = ""
    text_temp = ""
    @text.each_line do |line|
      if line =~ /[-|_]/
        text_temp << line
      else
        result << convert_lines(text_temp) + "," unless text_temp.empty?
        text_temp = ""
      end
    end
    result << convert_lines(text_temp)
    result
  end

  def convert_lines(text_temp)
    temp = text_temp.lines.each_with_object(Array.new) do |line, result|
      result << line.tr("\n", " ").scan(/.{3}/)
    end.delete_if{|e| e.empty?}

    temp = temp.shift.zip(*temp).map! do |e|
      e.join.strip
    end

    temp.each_with_object(String.new) do |e, result|
      result << (NUM.include?(e) ? NUM.index(e).to_s : "?")
    end
  end
end
