class OCR

  OCR_HASH = {
    " _ \n| |\n|_|\n   " => "0",
    "   \n  |\n  |\n   " => "1",
    " _ \n _|\n|_ \n   " => "2",
    " _ \n _|\n _|\n   " => "3",
    "   \n|_|\n  |\n   " => "4",
    " _ \n|_ \n _|\n   " => "5",
    " _ \n|_ \n|_|\n   " => "6",
    " _ \n  |\n  |\n   " => "7",
    " _ \n|_|\n|_|\n   " => "8",
    " _ \n|_|\n _|\n   " => "9"
  }

  def initialize(text)
    row_size = text.length / 4
    rows = text.scan(/.{1,#{row_size}}/)
    char_chunk = rows.map do |row|
      row.scan(/.{1,3}/) 
    end
    @processed_ocr_numbers = char_chunk.transpose.map do |number|
      number.join("\n")
    end
  end

  def convert
    @processed_ocr_numbers.map do |number|
      if OCR_HASH[number]
        OCR_HASH[number]
      else 
        "?"
      end
    end.join
  end
  
end
